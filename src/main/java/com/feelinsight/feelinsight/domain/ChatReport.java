package com.feelinsight.feelinsight.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
//대화분석
public class ChatReport {
    @Id @GeneratedValue
    private Long chatReportId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    private String emotion; //JSON 써야함
    private String happy;
    private String anxious;
    private String neutral;
    private String sad;
    private String angry;

    public ChatReport(User user, Chat chat, String emotion, String happy, String anxious, String neutral, String sad, String angry) {
        this.user = user;
        this.chat = chat;
        this.emotion = emotion;
        this.happy = happy;
        this.anxious = anxious;
        this.neutral = neutral;
        this.sad = sad;
        this.angry = angry;
    }
}
