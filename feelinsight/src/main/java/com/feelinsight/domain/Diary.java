package com.feelinsight.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@Getter
@Entity
public class Diary {
    @Id @GeneratedValue
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="writer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User writer;
    private Date date;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private String content;
    private String emotionAnalysis;


}
