package com.feelinsight.feelinsight.DTO;

import com.feelinsight.feelinsight.domain.Chat;
import com.feelinsight.feelinsight.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;


public class ChatDTO {
    @Data
    public static class ChatTransfer {
        @Schema(description = "유저 아이디", example = "1234")
        private Long userId;
        @Schema(description = "채팅 내용", example = "이건 stt를 통해서 작성된 어쩌구에요ㅎㅎㅎ")
        private String message;
        @Schema(description = "일기 작성 시작 시간", example = "2050-01-01T00:00:00.3")
        private LocalDateTime startTime;
        @Schema(description = "일기 작성 끝난 시간", example = "2050-01-01T00:05:00.3")
        private LocalDateTime endTime;
        @Schema(description = "감정", example = "{happiness:10, anxiety:10, neutral:20, sadness:30, anger:30}")
        private Map<String, Integer> emotions;
        @Schema(description = "감정 상황", example = "{happiness:'초콜릿 먹을 때', anxiety:'혼날 때', sadness:'혼자 있을 떄', anger:'부당할 때'}")
        private Map<String, String> situation;
    }

    @Data
    public static class ChatResponse{
        private Long chatId;
        private User user;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private String message;

        public ChatResponse(Chat chat){
            this.chatId = chat.getChatId();
            this.user = chat.getUser();
            this.startTime = chat.getStartTime();
            this.endTime = chat.getEndTime();
            this.message = chat.getMessage();
        }
    }
}