package com.feelinsight.feelinsight.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@Getter
@Entity
//사용자
public class User {
    @Id @GeneratedValue
    private Long userId;
    @Setter
    private String userName;
    @Column(unique = true)
    private String Id;
    @Column(unique = true)
    private String email;
    private String password;
    private String phoneNumber;
    private Date birthDate;
    private Gender gender;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String job;

    public User(String userName, String Id, String email, String password, String phoneNumber, Date birthDate, String job, Gender gender){
        this.userName=userName;
        this.Id=Id;
        this.email=email;
        this.password=password;
        this.phoneNumber=phoneNumber;
        this.birthDate=birthDate;
        this.createdAt=LocalDateTime.now();
        this.updatedAt=this.createdAt;
        this.job=job;
        this.gender=gender;
    }
    public void updateUser(String userName ,String phoneNumber, String job){
        this.userName=userName;
        this.phoneNumber=phoneNumber;
        this.updatedAt=LocalDateTime.now();
        this.job=job;
    }


    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    public void setPassword(String password){
        this.password=passwordEncoder.encode(password);
    }

    public boolean checkPassword(String rawPassword) {
        return passwordEncoder.matches(rawPassword, this.password);
    }
}
