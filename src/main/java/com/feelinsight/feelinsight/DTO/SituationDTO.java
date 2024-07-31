package com.feelinsight.feelinsight.DTO;

import com.feelinsight.feelinsight.domain.Situation;
import com.feelinsight.feelinsight.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

public class SituationDTO {
    @Data
    public static class SituationResponse{
        @Schema(description = "추천 활동 제목", example = "화를 다스리는 법")
        private Long situation;
        @Schema(description = "유저 이름", example = "박지우")
        private String userName;
        @Schema(description = "행복할 때", example = "초콜릿 먹을 때")
        private String happinessAt;
        @Schema(description = "불안할 때", example = "혼날 때")
        private String anxietyAt;
        @Schema(description = "슬플 때", example = "이별했을 때")
        private String sadnessAt;
        @Schema(description = "분노할 때", example = "부당한 일을 당했을 때")
        private String angerAt;


        public SituationResponse(Situation situation){
            this.situation = situation.getSituationId();
            this.userName = situation.getUser().getUserName();
            this.happinessAt = situation.getHappinessAt();
            this.anxietyAt = situation.getAnxietyAt();
            this.sadnessAt = situation.getSadnessAt();
            this.angerAt = situation.getAngerAt();
        }
    }
}
