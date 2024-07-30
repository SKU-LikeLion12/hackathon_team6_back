package com.feelinsight.feelinsight.repository;

import com.feelinsight.feelinsight.domain.Chat;
import com.feelinsight.feelinsight.domain.Emotion;




public interface EmotionRepositoryIN {
    Emotion saveEmotion(Emotion emotion);
    Emotion findByEmotionId(Long emotionId);
    Emotion findByUserIdEmotion(Long userId);
    void updateEmotion(Long userId, int happiness, int anxiety, int sadness, int anger, int neutral);
}
