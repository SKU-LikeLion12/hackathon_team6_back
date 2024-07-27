package com.feelinsight.feelinsight.repository;

import com.feelinsight.feelinsight.domain.Emotion;
import com.feelinsight.feelinsight.domain.Situation;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SituationRepository {

    private final EntityManager em;
    public Situation saveSituation(Situation situation) {
        em.persist(situation);
        return situation;
    }
}
