package com.feelinsight.feelinsight.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Emotion {
    @Id @GeneratedValue
    private long emotionId;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int happiness;
    private int anxiety;
    private int neutral;
    private int sadness;
    private int anger;

    public Emotion(User user, int happiness, int anxiety, int neutral, int sadness, int anger){
        this.user=user;
        this.happiness=happiness;
        this.anxiety=anxiety;
        this.neutral=neutral;
        this.sadness=sadness;
        this.anger=anger;
    }

}
