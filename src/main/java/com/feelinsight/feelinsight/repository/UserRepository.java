package com.feelinsight.feelinsight.repository;

import com.feelinsight.feelinsight.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository implements UserRepositoryIN{

    private final EntityManager em;

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public User findByUserId(Long userId) {
        return em.find(User.class, userId);
    }

    @Override
    public User findById(String id) {
        try{
            return em.createQuery("select u from User u where u.id=:id",User.class)
                    .setParameter("id",id).getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }

    @Override
    public User findByEmail(String email) {
        try{
            return em.createQuery("select u from User u where u.email=:email",User.class)
                    .setParameter("email",email).getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u",User.class).getResultList();
    }

    @Override
    public void deleteUser(User user) {
        em.remove(user);
    }

    @Override
    public List<User> findByUserName(String username) {
        return em.createQuery("select u from User u where u.userName=:username",User.class)
                .setParameter("username",username).getResultList();
    }

}
