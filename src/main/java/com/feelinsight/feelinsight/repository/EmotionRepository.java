package com.feelinsight.feelinsight.repository;

import com.feelinsight.feelinsight.domain.Chat;
import com.feelinsight.feelinsight.domain.Emotion;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmotionRepository implements EmotionRepositoryIN{

    private final EntityManager em;
    @Override
    public Emotion saveEmotion(Emotion emotion) {
        em.persist(emotion);
        return emotion;
    }

    @Override
    public Emotion findByEmotionId(Long emotionId) {
        return em.find(Emotion.class, emotionId);    }
}
