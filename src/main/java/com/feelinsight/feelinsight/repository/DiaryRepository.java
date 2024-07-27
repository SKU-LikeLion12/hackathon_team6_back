package com.feelinsight.feelinsight.repository;

import com.feelinsight.feelinsight.domain.Diary;
import com.feelinsight.feelinsight.domain.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DiaryRepository implements DiaryRepositoryIN{

    private final EntityManager em;
    private UserRepository userRepository;

    @Override
    public Diary saveDiary(Diary diary) {
        em.persist(diary);
        return diary;
    }

    @Override
    public void deleteDiary(Diary diary) {
        em.remove(diary);
    }

    @Override
    public Diary findByDiaryId(Long diaryId) {
        return em.find(Diary.class, diaryId);
    }

    @Override
    public Diary findByuserIdAndDate(Long userId, LocalDate date){
        User user= userRepository.findByUserId(userId);
        return em.createQuery("select d from Diary d where d.user=:u and d.date=:date", Diary.class)
                .setParameter("u",user)
                .setParameter("date",date)
                .getSingleResult();
    }

    @Override
    public List<Diary> findByAll() {
        return em.createQuery("select d from Diary d",Diary.class).getResultList();
    }

    @Override
    public List<Diary> findByUserAll(Long userId) {
        User user=userRepository.findByUserId(userId);
        return em.createQuery("select d from Diary d where d.user=:u",Diary.class)
                .setParameter("u",user)
                .getResultList();
    }

    @Override
    public List<Diary> findByUserIdAndMonth(Long userId, LocalDate month){
        User user=userRepository.findByUserId(userId);
        return em.createQuery("select d from Diary d where d.user=:u and year(d.date) = year(:month) and month(d.date)=month(:month)",Diary.class)
                .setParameter("u",user)
                .setParameter("month",month)
                .getResultList();
    }
}
