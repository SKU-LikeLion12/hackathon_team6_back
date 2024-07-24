package com.feelinsight.feelinsight.service;

import com.feelinsight.feelinsight.domain.Gender;
import com.feelinsight.feelinsight.domain.User;
import com.feelinsight.feelinsight.exception.IdNotFoundException;
import com.feelinsight.feelinsight.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtility jwtUtility;

    public User tokenToUser(String token){
        return userRepository.findById(jwtUtility.validateToken(token).getSubject());
    }

    @Transactional
    public User updateUser(String token, String userName, String email, String phoneNumber, String job){
        User user=tokenToUser(token);
        user.updateUser(userName, phoneNumber, email, job);
        return userRepository.save(user);
    }

    @Transactional
    public User signUp(String userName, String Id, String email, String password, String phoneNumber, LocalDate birthDate, Gender gender, String job){
        User user=userRepository.findById(Id);
        if(user!=null) return null;
        return userRepository.save(new User(userName, Id, email, password, phoneNumber, birthDate, job, gender));
    }

    public User findByUserId(Long userId){return userRepository.findByUserId(userId);}

    public String login(String Id, String passwd){
        User user=findById(Id);
        if(user!=null && user.checkPassword(passwd)){
            return jwtUtility.generateToken(user.getId());
        }
        return null;
    }

    public User findById(String Id){
        User user=userRepository.findById(Id);
        if(user==null) throw new IdNotFoundException();
        return user;
    }

    public User findByEmail(String email){
        User user= userRepository.findByEmail(email);
        if(user==null) throw new IdNotFoundException();
        return user;
    }

    public List<User> findByUserName(String userName){ return userRepository.findByUserName(userName);}

    public  List<User> findAll(){return userRepository.findAll();}

    @Transactional
    public boolean deleteUser(String token){
        User user=tokenToUser(token);
        if(user==null) return false;
        userRepository.deleteUser(user);
        return true;
    }
}

