package com.feelinsight.feelinsight.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Situation {
    @Id
    @GeneratedValue
    private Long situationId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String happinessAt;
    private String anxietyAt;
    private String sadnessAt;
    private String angerAt;

    public Situation(User user, String happinessAt, String anxietyAt, String sadnessAt, String angerAt){
        this.user = user;
        this.happinessAt = happinessAt;
        this.anxietyAt = anxietyAt;
        this.sadnessAt = sadnessAt;
        this.angerAt = angerAt;
    }



}
