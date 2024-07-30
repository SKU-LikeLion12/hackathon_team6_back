package com.feelinsight.feelinsight.controller;

import com.feelinsight.feelinsight.DTO.EmotionDTO;
import com.feelinsight.feelinsight.DTO.EmotionDTO.EmotionResponse;
import com.feelinsight.feelinsight.domain.Emotion;
import com.feelinsight.feelinsight.service.EmotionService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EmotionController {
    private final EmotionService emotionService;
    @GetMapping("/emotion/{emotionId}")
    public EmotionResponse getemotion(@Parameter(description = "emotion ID", example = "test_id")@PathVariable("id") Long emotionId){
        Emotion emotion = emotionService.findByEmotionId(emotionId);
        EmotionResponse emotionresponse = new EmotionResponse(emotion);
        return emotionresponse;
    }

    @PostMapping("/emotion/update")
    public void updateEmotion(@RequestBody EmotionDTO.RequestEmotion request){
        emotionService.updateEmotion(
                request.getUserId(),
                request.getHappiness(),
                request.getAnxiety(),
                request.getNeutral(),
                request.getSadness(),
                request.getAnger());
    }

}
