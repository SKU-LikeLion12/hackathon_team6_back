package com.feelinsight.feelinsight.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
//개인별 추천활동 리스트
public class Post {
    @Id @GeneratedValue
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userReport_id")
    private UserReport userReport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private String title;

    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Post(UserReport userReport, User user, String title, String content){
        this.userReport=userReport;
        this.user=user;
        this.title=title;
        this.content=content;
        this.createAt=LocalDateTime.now();
        this.updateAt=this.createAt;
    }

    public void updatePost(String content){
        this.content=content;
        this.updateAt=LocalDateTime.now();
    }

}
