package com.feelinsight.feelinsight.DTO;

import com.feelinsight.feelinsight.domain.Diary;
import com.feelinsight.feelinsight.domain.Emotion;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

public class DiaryDTO {

    @Data
    @AllArgsConstructor
    public static class ResponseDiary{
        @Schema(description = "날짜", example = "2050-01-01")
        private LocalDate date;
        @Schema(description = "일기 내용", example = "오늘의 일기는 다음과 같아 어쩌구...")
        private String content;
        @Schema(description = "유저 이름", example = "박지우")
        private String userName;
        @Schema(description = "감정", example = "happiness:10, anxiety:10, neutral:20, sadness:30, anger:30")
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
