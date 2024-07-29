package com.feelinsight.feelinsight.DTO;

import com.feelinsight.feelinsight.domain.Chat;
import com.feelinsight.feelinsight.domain.Situation;
import com.feelinsight.feelinsight.domain.User;
import java.time.LocalDateTime;
import lombok.Data;

public class SituationDTO {
    @Data
    public static class SituationResponse{
        private Long situation;
        private User user;
        private String happinessAt;
        private String anxietyAt;
        private String sadnessAt;
        private String angerAt;


        public SituationResponse(Situation situation){
            this.situation = situation.getSituationId();
            this.user = situation.getUser();
            this.happinessAt = situation.getHappinessAt();
            this.anxietyAt = situation.getAnxietyAt();
            this.sadnessAt = situation.getSadnessAt();
            this.angerAt = situation.getAngerAt();
        }
    }
}
