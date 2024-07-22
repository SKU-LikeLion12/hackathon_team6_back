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
    @JoinColumn(name="chatReport_id")
    private ChatReport chatReport;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    private LocalDate date;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    private String content;

    public Diary(User user, LocalDate date, String content, ChatReport chatReport){
        this.user=user;
        this.chatReport=chatReport;
        this.date=date;
        this.content=content;
        this.createAt=LocalDateTime.now();
        this.updateAt=this.createAt;
    }

    public void updateDiary(String content){
        this.content=content;
        this.updateAt=LocalDateTime.now();
    }

}
