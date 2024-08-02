package com.feelinsight.feelinsight.service;


import com.feelinsight.feelinsight.DTO.ChatDTO;
import com.feelinsight.feelinsight.domain.Emotion;
import com.feelinsight.feelinsight.exception.EmotionNotFoundException;
import com.feelinsight.feelinsight.repository.EmotionRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmotionService {
    private final EmotionRepository emotionRepository;

    @Transactional
    public void processEmotionData(ChatDTO.ChatTransfer chatTransfer) {
        try{
            Emotion emotion = convertToEmotion(chatTransfer);
            emotionRepository.saveEmotion(emotion);
        }catch(Exception e){
            throw new RuntimeException("감정 데이터를 처리하는 중 오류가 발생했습니다.",e);
        }
    }
    private Emotion convertToEmotion(ChatDTO.ChatTransfer chatTransfer) {
        Emotion entity = new Emotion();
        Map<String, Integer> emotions = chatTransfer.getEmotions();

        entity.setHappiness(emotions.getOrDefault("행복", 0));
        entity.setAnxiety(emotions.getOrDefault("불안", 0));
        entity.setNeutral(emotions.getOrDefault("중립", 0));
        entity.setSadness(emotions.getOrDefault("슬픔", 0));
        entity.setAnger(emotions.getOrDefault("분노", 0));

        return entity;
    }

    public void updateEmotion(Long userId, int happiness, int anxiety, int neutral, int sadness, int anger){
        try{
            emotionRepository.updateEmotion(userId, happiness, anxiety, neutral, sadness, anger);
        }catch (Exception e){
            throw new RuntimeException("감정 데이터를 업데이트하는 중 오류가 발생했습니다.");
        }

    }
    public Emotion findByEmotionId(Long emotionId){
        Emotion emotion = emotionRepository.findByEmotionId(emotionId);
        if(emotion==null){
            throw new EmotionNotFoundException("해당 ID의 감정데이터를 찾을 수 없습니다.");
        }
        return emotion;
    }
}
