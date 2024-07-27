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



    private String emotion;//Json써야함
    private String happy;
    private String anxious;
    private String neutral;
    private String sad;
    private String angry;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;


    public UserReport(User user, String emotion, String happy, String anxious, String neutral, String sad, String angry) {
        this.user = user;
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
    }
}
