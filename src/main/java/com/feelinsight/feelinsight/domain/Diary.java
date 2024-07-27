package com.feelinsight.feelinsight.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
//일기
public class Diary {
    @Id @GeneratedValue
    private long diaryId;

    @OneToOne
    @JoinColumn(name="emotion_id")
    private Emotion emotion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @OneToOne
    @JoinColumn(name="chat_id")
    private Chat chat;

    private LocalDate date;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Diary(User user, LocalDate date, Chat chat, Emotion emotion){
        this.user=user;
        this.emotion = emotion;
        this.date=date;
        this.chat=chat;
        this.createAt=LocalDateTime.now();
        this.updateAt=this.createAt;
    }

    public void updateDiary(Chat chat){
        this.chat=chat;
        this.updateAt=LocalDateTime.now();
    }

}
