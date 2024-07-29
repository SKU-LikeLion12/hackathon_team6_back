package com.feelinsight.feelinsight.repository;

import com.feelinsight.feelinsight.domain.Chat;
import com.feelinsight.feelinsight.domain.Emotion;

public interface EmotionRepositoryIN {
    Emotion saveEmotion(Emotion emotion);
    Emotion findByEmotionId(Long emotionId);
}
