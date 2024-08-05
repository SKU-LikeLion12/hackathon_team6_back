package com.feelinsight.feelinsight.repository;

import com.feelinsight.feelinsight.domain.Situation;
import com.feelinsight.feelinsight.domain.User;
import com.feelinsight.feelinsight.service.UserService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SituationRepository implements SituationRepositoryIN {
    private final EntityManager em;
    private final UserService userService;
    @Override
    public Situation saveSituation(Situation situation) {
        em.persist(situation);
        return situation;
    }

    @Override
    public Situation findBySituationId(Long situationId) {
        return em.find(Situation.class, situationId);
    }

    @Override
    public Situation findSituationByUser(Long userId) {
        User user=userService.findByUserId(userId);
        return em.createQuery("select s from Situation s where s.user=:u",Situation.class)
                .setParameter("u",user).getSingleResult();
    }
}
