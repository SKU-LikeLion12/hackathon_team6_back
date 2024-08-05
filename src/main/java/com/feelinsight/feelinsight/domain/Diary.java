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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Chat chat;

    private String content;

    private LocalDate date;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;


    public Diary(User user, LocalDate date, Emotion emotion, Chat chat){
        this.user=user;
        this.date=date;
        this.content=chat.getMessage();
        this.emotion=emotion;
        this.chat=chat;
        this.createAt=LocalDateTime.now();
        this.updateAt=this.createAt;
    }

    public void updateDiary(String content){
        this.chat.setMessage(content);
        this.updateAt=LocalDateTime.now();
    }

    public String getChatMessage(){
        return this.chat.getMessage();
    }

}
