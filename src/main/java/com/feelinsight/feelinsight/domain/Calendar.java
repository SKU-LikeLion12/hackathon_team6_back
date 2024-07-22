package com.feelinsight.feelinsight.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
//캘린더
public class Calendar {
    @Id @GeneratedValue
    private long calendarId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    private LocalDate date;
    private Emotion emotion;

    public Calendar(User user, LocalDate date, Emotion emotion){
        this.user=user;
        this.date=date;
        this.emotion=emotion;
    }

}
