package com.feelinsight.feelinsight.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import lombok.Setter;

@NoArgsConstructor
@Getter@Setter
@Entity
public class Chat {
    @Id @GeneratedValue
    private Long chatId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String message;

    public Chat(User user, String message) {
        this.user = user;
        this.startTime =LocalDateTime.now();
        this.message = message;
    }

    public void endChat() {
        this.endTime = LocalDateTime.now();
    }
}
