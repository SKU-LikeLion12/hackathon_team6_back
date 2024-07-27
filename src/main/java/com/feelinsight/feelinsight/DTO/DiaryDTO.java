package com.feelinsight.feelinsight.DTO;

import com.feelinsight.feelinsight.domain.Diary;
import com.feelinsight.feelinsight.domain.Emotion;
import com.feelinsight.feelinsight.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

public class DiaryDTO {

    @Data
    @AllArgsConstructor
    public static class ResponseDiary{
        private LocalDate date;
        private String content;
        private String userName;
        private Emotion emotion;

        public ResponseDiary(Diary diary){
            this.date=diary.getDate();
            this.content=diary.getChatMessage();
            this.userName=diary.getUser().getUserName();
            this.emotion= diary.getEmotion();
        }

    }

    @Data
    public static class RequestDiary{
        private String token;
        private String content;
        private LocalDate date;
        private Emotion emotion;
    }
}
