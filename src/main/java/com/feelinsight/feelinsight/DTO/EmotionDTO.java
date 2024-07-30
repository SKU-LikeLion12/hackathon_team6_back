package com.feelinsight.feelinsight.DTO;

import com.feelinsight.feelinsight.domain.Emotion;
import com.feelinsight.feelinsight.domain.User;
import lombok.Data;

public class EmotionDTO {
    @Data
    public static class EmotionResponse{
        private Long emotionId;
        private User user;
        private int happiness;
        private int anxiety;
        private int neutral;
        private int sadness;
        private int anger;


        public EmotionResponse(Emotion emotion){
            this.emotionId = emotion.getEmotionId();
            this.user = emotion.getUser();
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
