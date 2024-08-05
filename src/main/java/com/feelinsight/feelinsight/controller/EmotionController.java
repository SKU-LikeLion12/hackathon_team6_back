package com.feelinsight.feelinsight.controller;

import com.feelinsight.feelinsight.DTO.EmotionDTO;
import com.feelinsight.feelinsight.DTO.EmotionDTO.EmotionResponse;
import com.feelinsight.feelinsight.domain.Emotion;
import com.feelinsight.feelinsight.domain.User;
import com.feelinsight.feelinsight.exception.EmotionNotFoundException;
import com.feelinsight.feelinsight.exception.InvalidTokenException;
import com.feelinsight.feelinsight.service.EmotionService;
import com.feelinsight.feelinsight.service.JwtUtility;
import com.feelinsight.feelinsight.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class EmotionController {
    private final EmotionService emotionService;
    private final JwtUtility jwtUtility;
    private final UserService userService;

    @Operation(summary = "감정 조회", description = "emotion_Id로 emotion 조회",
            responses = {@ApiResponse(responseCode = "200", description = "조회 성공"),
                    @ApiResponse(responseCode = "404", description = "감정을 찾을 수 없음")})
    @GetMapping("/emotion/{emotionId}")
    public ResponseEntity<EmotionResponse> getEmotion(@Parameter(description = "emotion ID", example = "test_id")@PathVariable("emotionId") Long emotionId){
        try{
            Emotion emotion = emotionService.findByEmotionId(emotionId);
            EmotionResponse emotionresponse = new EmotionResponse(emotion);
            return ResponseEntity.ok(emotionresponse);
        }catch (EmotionNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "감정 수정", description = "emotion을 입력받아 감정 수정",
            responses = {@ApiResponse(responseCode = "200", description = "업데이트 성공")})
    @PostMapping("/emotion/update")
    public ResponseEntity<Void> updateEmotion(@RequestBody EmotionDTO.RequestEmotion request) {
        emotionService.updateEmotion(
                request.getUserId(),
                request.getHappiness(),
                request.getAnxiety(),
                request.getNeutral(),
                request.getSadness(),
                request.getAnger());
        return ResponseEntity.ok().build();

    }

    @Operation(summary = "감정 조회", description = "유저와 날짜정보로 감정 조회",
            responses = {@ApiResponse(responseCode = "200", description = "성공"),
                    @ApiResponse(responseCode = "404", description = "emotion 찾을 수 없음"),
                    @ApiResponse(responseCode = "401", description = "유효하지 않은 토큰")})
    @GetMapping("/emotion")
    public ResponseEntity<String> getEmotionByUserAndDate(@RequestHeader("Authorization") String token, @RequestParam LocalDate date ){
        try{
            String userToken=jwtUtility.bearerToken(token);
            User user= userService.tokenToUser(userToken);
            Emotion emotion=emotionService.findByUserAndDate(user.getUserId(), date);
            Map<String, Integer> emotions = new HashMap<>();
            emotions.put("happy", emotion.getHappiness());
            emotions.put("sad", emotion.getAnxiety());
            emotions.put("angry", emotion.getNeutral());
            emotions.put("fear", emotion.getSadness());
            emotions.put("disgust", emotion.getAnger());

            String topEmotion = emotions.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse("unknown");

            return ResponseEntity.ok(topEmotion);

        }catch(EmotionNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }catch(InvalidTokenException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @Operation(summary = "감정 조회", description = "유저 감정 조회",
            responses = {@ApiResponse(responseCode = "200", description = "성공"),
                    @ApiResponse(responseCode = "404", description = "emotion 찾을 수 없음"),
                    @ApiResponse(responseCode = "401", description = "유효하지 않은 토큰")})
    @GetMapping("/emotion/user")
    public ResponseEntity<Emotion> getUserEmotion(@RequestHeader("Authorization") String token){
        try{
            String userToken= jwtUtility.bearerToken(token);
            User user=userService.tokenToUser(userToken);
            Emotion emotion=emotionService.findByUserEmotion(user.getUserId());
            return ResponseEntity.ok(emotion);
        }catch(EmotionNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }catch(InvalidTokenException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

}
