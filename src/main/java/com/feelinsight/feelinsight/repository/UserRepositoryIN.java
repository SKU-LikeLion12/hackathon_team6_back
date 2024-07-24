package com.feelinsight.feelinsight.repository;

import com.feelinsight.feelinsight.domain.User;

import java.util.List;

public interface UserRepositoryIN {
    User save(User user);
    User findByUserId(Long userId);
    User findById(String Id);
    User findByEmail(String email);

    List<User> findAll();
    void deleteUser(User user);
    List<User> findByUserName(String username);

}
