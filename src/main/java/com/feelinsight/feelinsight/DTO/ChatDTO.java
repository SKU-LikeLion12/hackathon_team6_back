package com.feelinsight.feelinsight.DTO;

import com.feelinsight.feelinsight.domain.Chat;
import com.feelinsight.feelinsight.domain.User;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;


public class ChatDTO {
    @Data
    public static class ChatTransfer {
        private Long userId;
        private String message;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private Map<String, Integer> emotions;
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