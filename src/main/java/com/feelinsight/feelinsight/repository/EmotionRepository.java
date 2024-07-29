package com.feelinsight.feelinsight.repository;

import com.feelinsight.feelinsight.domain.Emotion;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmotionRepository {

    private final EntityManager em;
    public Emotion saveEmotion(Emotion emotion) {
        em.persist(emotion);
        return emotion;
    }
}
