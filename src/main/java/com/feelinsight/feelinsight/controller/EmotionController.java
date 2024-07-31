package com.feelinsight.feelinsight.controller;

import com.feelinsight.feelinsight.DTO.EmotionDTO;
import com.feelinsight.feelinsight.DTO.EmotionDTO.EmotionResponse;
import com.feelinsight.feelinsight.domain.Emotion;
import com.feelinsight.feelinsight.exception.EmotionNotFoundException;
import com.feelinsight.feelinsight.service.EmotionService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EmotionController {
    private final EmotionService emotionService;
    @GetMapping("/emotion/{emotionId}")
    public ResponseEntity<EmotionResponse> getemotion(@Parameter(description = "emotion ID", example = "test_id")@PathVariable("id") Long emotionId){
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
