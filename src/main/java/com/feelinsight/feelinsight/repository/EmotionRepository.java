package com.feelinsight.feelinsight.repository;

import com.feelinsight.feelinsight.domain.Emotion;
import com.feelinsight.feelinsight.domain.User;
import com.feelinsight.feelinsight.service.UserService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmotionRepository implements EmotionRepositoryIN{

    private final EntityManager em;
    private final UserService userService;
    @Override
    public Emotion saveEmotion(Emotion emotion) {
        em.persist(emotion);
        return emotion;
    }

    @Override
    public Emotion findByEmotionId(Long emotionId) {
        return em.find(Emotion.class, emotionId);
    }

    @Override
    public void updateEmotion(Long userId, int happiness, int anxiety, int neutral, int sadness, int anger) {
        Emotion emotion=findByUserIdEmotion(userId);
        if(emotion!=null){
            emotion.updateEmotion(happiness,anxiety, neutral,sadness,anger);
            em.merge(emotion);
        }else{
            User user=userService.findByUserId(userId);
            emotion=new Emotion(user, happiness, anxiety, neutral, sadness, anger);
            em.persist(emotion);
        }
    }

    @Override
    public Emotion findByUserIdEmotion(Long userId) {
        User user=userService.findByUserId(userId);
        try{
            return em.createQuery("select e from Emotion e where e.user=:u",Emotion.class)
                    .setParameter("u",user)
                    .getSingleResult();
        }catch(Exception e){
            return null;
        }

    }
}
