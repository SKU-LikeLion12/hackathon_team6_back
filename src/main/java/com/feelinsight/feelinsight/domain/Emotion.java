package com.feelinsight.feelinsight.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter @Setter
@Entity
public class Emotion {
    @Id @GeneratedValue
    private Long emotionId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int happiness;
    private int anxiety;
    private int neutral;
    private int sadness;
    private int anger;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Emotion(User user, int happiness, int anxiety, int neutral, int sadness, int anger) {
        this.user = user;
        this.happiness = happiness;
        this.anxiety = anxiety;
        this.neutral = neutral;
        this.sadness = sadness;
        this.anger = anger;
        this.createAt=LocalDateTime.now();
        this.updateAt=this.createAt;
        normalize();
    }

    public void updateEmotion(int happiness, int anxiety, int neutral, int sadness, int anger) {
        double weight=0.2;
        this.happiness=(int)Math.round(this.happiness*(1-weight)+happiness*weight);
        this.anxiety=(int)Math.round(this.anxiety*(1-weight)+anxiety*weight);
        this.sadness=(int)Math.round(this.sadness*(1-weight)+sadness*weight);
        this.anger=(int)Math.round(this.anger*(1-weight)+anger*weight);
        this.neutral=(int)Math.round(this.neutral*(1-weight)+neutral*weight);
        this.updateAt=LocalDateTime.now();
        normalize();
    }

    private void normalize() {
        int total=happiness+anxiety+sadness+anger+neutral;
        if(total>0) {
            this.happiness = (happiness * 100) / total;
            this.anxiety = (anxiety * 100) / total;
            this.sadness = (sadness * 100) / total;
            this.anger = (anger * 100) / total;
            this.neutral = (neutral * 100) / total;
        }
    }

    public String getNagativeEmotion(){
        if(anxiety>=sadness && anxiety>=anger){
            return "anxiety";
        }else if(sadness >= anxiety && sadness>=anger){
            return "sadness";
        }else{
            return "anger";
        }
    }
}
