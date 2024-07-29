package com.feelinsight.feelinsight.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter@Setter
@Entity
//사용자
public class User {
    @Id @GeneratedValue
    private Long userId;
    @Setter
    private String userName;
    @Column(unique = true)
    private String id;
    @Column(unique = true)
    private String email;
    private String password;
    private String phoneNumber;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String job;

    public User(String userName, String Id, String email, String password, String phoneNumber, LocalDate birthDate, String job, Gender gender){
        this.userName=userName;
        this.id =Id;
        this.email=email;
        this.setPassword(password);
        this.phoneNumber=phoneNumber;
        this.birthDate=birthDate;
        this.createdAt=LocalDateTime.now();
        this.updatedAt=this.createdAt;
        this.job=job;
        this.gender=gender;
    }
    public void updateUser(String userName ,String phoneNumber, String email, String job){
        if(userName!=null) this.userName=userName;
        if(phoneNumber!=null) this.phoneNumber=phoneNumber;
        if(email!=null) this.email=email;
        if(job!=null) this.job=job;
        this.updatedAt=LocalDateTime.now();
    }


    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    public void setPassword(String password){
        this.password=passwordEncoder.encode(password);
    }

    public boolean checkPassword(String rawPassword) {
        System.out.println("passwordEncoder = " + passwordEncoder.matches(rawPassword,this.password));
        return passwordEncoder.matches(rawPassword, this.password);
    }
}
