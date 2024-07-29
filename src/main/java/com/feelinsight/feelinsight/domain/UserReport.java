package com.feelinsight.feelinsight.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @JoinColumn(name = "emotion_id")
    private Emotion emotion;


    @OneToOne
    @JoinColumn(name = "situation_id")
    private Situation situation;

    private String happinessAtTotal;
    private String anxietyAtTotal;
    private String sadnessAtTotal;
    private String angerAtTotal;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;


    public UserReport(User user, Emotion emotion, String happinessAtTotal, String anxietyAtTotal, String sadnessAtTotal, String angerAtTotal) {
        this.user = user;
        this.emotion = emotion;
        this.happinessAtTotal = happinessAtTotal;
        this.anxietyAtTotal = anxietyAtTotal;
        this.sadnessAtTotal = sadnessAtTotal;
        this.angerAtTotal = angerAtTotal;
        this.createAt=LocalDateTime.now();
        this.updateAt=this.createAt;
    }

    public void updateUserReport(Emotion emotion, String happinessAtTotal, String anxietyAtTotal, String sadnessAtTotal, String angerAtTotal){
        this.emotion = emotion;
        this.happinessAtTotal = happinessAtTotal;
        this.anxietyAtTotal = anxietyAtTotal;
        this.sadnessAtTotal = sadnessAtTotal;
        this.angerAtTotal = angerAtTotal;
        this.updateAt=LocalDateTime.now();
    }
}
