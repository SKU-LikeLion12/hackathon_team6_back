package com.feelinsight.domain;

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
public class User {

    @Id @GeneratedValue
    private Long id;
    @Column(unique = true)
    @Setter
    private String userName;
    @Column(unique = true)
    private String userId;
    @Column(unique = true)
    private String email;
    private String password;
    private String phoneNumber;
    private Date birthDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Role role= Role.USER;
    private String job;

    public User(String userName, String userId, String email, String password, String phoneNumber, Date birthDate, String job){
        this.userName=userName;
        this.userId=userId;
        this.email=email;
        this.password=password;
        this.phoneNumber=phoneNumber;
        this.birthDate=birthDate;
        this.createdAt=LocalDateTime.now();
        this.updatedAt=this.createdAt;
        this.job=job;
    }
    public void updateUser(String userName ,String phoneNumber, String job){
        this.userName=userName;
        this.phoneNumber=phoneNumber;
        this.updatedAt=LocalDateTime.now();
        this.job=job;
    }

    public void changeRole(Role newRole){
        this.role=newRole;
    }

    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    public void setPassword(String password){
        this.password=passwordEncoder.encode(password);
    }

    public boolean checkPassword(String rawPassword) {
        return passwordEncoder.matches(rawPassword, this.password);
    }
}
