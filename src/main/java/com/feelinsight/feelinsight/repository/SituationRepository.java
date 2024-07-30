package com.feelinsight.feelinsight.repository;

import com.feelinsight.feelinsight.domain.Situation;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SituationRepository implements SituationRepositoryIN {
    private final EntityManager em;
    @Override
    public Situation saveSituation(Situation situation) {
        em.persist(situation);
        return situation;
    }

    @Override
    public Situation findBySituationId(Long situationId) {
        return em.find(Situation.class, situationId);
    }
}
