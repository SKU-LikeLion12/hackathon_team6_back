package com.feelinsight.feelinsight.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
//유저 분석
public class UserReport {
    @Id @GeneratedValue
    private Long userReportId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "chatReport_id")
    private ChatReport chatReport;

    private String emotion;//Json써야함
    private String happy;
    private String anxious;
    private String neutral;
    private String sad;
    private String angry;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;


    public UserReport(User user, ChatReport chatReport, String emotion, String happy, String anxious, String neutral, String sad, String angry) {
        this.user = user;
        this.chatReport = chatReport;
        this.emotion = emotion;
        this.happy = happy;
        this.anxious = anxious;
        this.neutral = neutral;
        this.sad = sad;
        this.angry = angry;
        this.createAt=LocalDateTime.now();
        this.updateAt=this.createAt;
    }

    public void updateUserReport(String emotion){
        this.emotion=emotion; //여기도 Json으로 바꿔서 해야됨
        this.updateAt=LocalDateTime.now();
    }
}
