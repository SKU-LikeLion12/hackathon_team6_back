package com.feelinsight.feelinsight.DTO;

import com.feelinsight.feelinsight.domain.Diary;
import com.feelinsight.feelinsight.domain.Emotion;
import lombok.Data;
import org.springframework.cglib.core.Local;


import java.time.LocalDate;
import java.util.Map;

public class CalendarDTO {
    @Data
    public static class ResponseCalendar{

        private Long userId;
        private Long diaryId;
        private LocalDate date;
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
