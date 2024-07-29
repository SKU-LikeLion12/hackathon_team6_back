package com.feelinsight.feelinsight.service;


import com.feelinsight.feelinsight.DTO.ChatDTO;
import com.feelinsight.feelinsight.domain.Emotion;
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

    public void processEmotionData(ChatDTO.ChatTransfer chatTransfer) {
        Emotion emotion = convertToEmotion(chatTransfer);
        emotionRepository.saveEmotion(emotion);
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
//
//
//    public Emotion getEmotionByChatId(Long chatId) {
//        return emotionRepository.findByChatId(chatId)
//                .orElseThrow(() -> new EntityNotFoundException("Emotion not found for chat id: " + chatId));
//    }
}
