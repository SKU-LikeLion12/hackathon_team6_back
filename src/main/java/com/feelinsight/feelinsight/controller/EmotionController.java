package com.feelinsight.feelinsight.controller;

import com.feelinsight.feelinsight.DTO.EmotionDTO;
import com.feelinsight.feelinsight.DTO.EmotionDTO.EmotionResponse;
import com.feelinsight.feelinsight.domain.Emotion;
import com.feelinsight.feelinsight.exception.EmotionNotFoundException;
import com.feelinsight.feelinsight.service.EmotionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EmotionController {
    private final EmotionService emotionService;

    @Operation(summary = "감정 조회", description = "emotion_Id로 emotion 조회",
            responses = {@ApiResponse(responseCode = "200", description = "조회 성공"),
                    @ApiResponse(responseCode = "404", description = "감정을 찾을 수 없음"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")})
    @GetMapping("/emotion/{emotionId}")
    public ResponseEntity<EmotionResponse> getEmotion(@Parameter(description = "emotion ID", example = "test_id")@PathVariable("id") Long emotionId){
        try{
            Emotion emotion = emotionService.findByEmotionId(emotionId);
            EmotionResponse emotionresponse = new EmotionResponse(emotion);
            return ResponseEntity.ok(emotionresponse);
        }catch (EmotionNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation(summary = "감정 수정", description = "emotion을 입력받아 감정 수정",
            responses = {@ApiResponse(responseCode = "200", description = "업데이트 성공"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")})
    @PostMapping("/emotion/update")
    public ResponseEntity<Void> updateEmotion(@RequestBody EmotionDTO.RequestEmotion request) {
        try {
            emotionService.updateEmotion(
                    request.getUserId(),
                    request.getHappiness(),
                    request.getAnxiety(),
                    request.getNeutral(),
                    request.getSadness(),
                    request.getAnger());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
