package com.feelinsight.feelinsight.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
//챗봇 대화 데이터
public class Chat {
    @Id @GeneratedValue
    private Long chatId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String message; //JSON써야함


    public Chat(User user, String message) {
        this.user = user;
        this.startTime =LocalDateTime.now();
        this.message = message;
    }

    public void endChat() {
        this.endTime = LocalDateTime.now();
    }

}
