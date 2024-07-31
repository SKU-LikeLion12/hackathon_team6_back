package com.feelinsight.feelinsight.DTO;

import com.feelinsight.feelinsight.domain.Emotion;
import com.feelinsight.feelinsight.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

public class EmotionDTO {
    @Data
    public static class EmotionResponse{
        @Schema(description = "감정 아이디", example = "test_emotion_id")
        private Long emotionId;
        @Schema(description = "유저 이름", example = "박지우")
        private String userName;
        @Schema(description = "행복", example = "10")
        private int happiness;
        @Schema(description = "불안", example = "20")
        private int anxiety;
        @Schema(description = "중립", example = "30")
        private int neutral;
        @Schema(description = "슬픔", example = "20")
        private int sadness;
        @Schema(description = "분노", example = "20")
        private int anger;


        public EmotionResponse(Emotion emotion){
            this.emotionId = emotion.getEmotionId();
            this.userName = emotion.getUser().getUserName();
            this.happiness = emotion.getHappiness();
            this.anxiety = emotion.getAnxiety();
            this.sadness = emotion.getSadness();
            this.anger = emotion.getAnger();
        }
    }

    @Data
    public static class RequestEmotion{
        private Long userId;
        private int happiness;
        private int anxiety;
        private int neutral;
        private int sadness;
        private int anger;
    }
}
