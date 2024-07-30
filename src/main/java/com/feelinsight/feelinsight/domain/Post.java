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

    private String title;
    private String content;
    private String emotionType;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Post(String title, String content, String emotionType){
        this.title=title;
        this.content=content;
        this.emotionType=emotionType;
        this.createAt=LocalDateTime.now();
        this.updateAt=this.createAt;
    }

    public void updatePost(String title,String content, String emotionType){
        this.title=title;
        this.content=content;
        this.emotionType=emotionType;
        this.updateAt=LocalDateTime.now();
    }

}
