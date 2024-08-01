package com.feelinsight.feelinsight.DTO;

import com.feelinsight.feelinsight.domain.Diary;
import com.feelinsight.feelinsight.domain.Emotion;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


import java.time.LocalDate;
import java.util.Map;

public class CalendarDTO {
    @Data
    public static class ResponseCalendar{
        @Schema(description = "유저 아이디", example = "1234")
        private Long userId;
        @Schema(description = "일기 아이디", example = "12")
        private Long diaryId;
        @Schema(description = "날짜(년월일)", example = "2050-01-01")
        private LocalDate date;
        @Schema(description = "감정", example = "happiness:10, anxiety:10, neutral:20, sadness:30, anger:30")
        private Emotion emotion;

        public ResponseCalendar(Diary diary){
            this.userId=diary.getUser().getUserId();
            this.diaryId=diary.getDiaryId();
            this.date=diary.getDate();
            this.emotion=diary.getEmotion();
        }
    }

    @Data
    public static class TopEmotionResponse{
        private Map<LocalDate, String> topEmotions;

        public TopEmotionResponse(Map<LocalDate, String> topEmotions){
            this.topEmotions=topEmotions;
        }
    }
    @Data
    public static class RequestCalendar{
        private String token;
        private LocalDate date;
    }
}
