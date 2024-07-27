package com.feelinsight.feelinsight.service;


import com.feelinsight.feelinsight.DTO.ChatDTO;
import com.feelinsight.feelinsight.domain.Chat;
import com.feelinsight.feelinsight.domain.Emotion;
import com.feelinsight.feelinsight.repository.EmotionRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmotionService {
    private final EmotionRepository emotionRepository;

//    Django에서 처리된 데이터 저장
    public void processEmotionData(ChatDTO chatData){
        Emotion emotion = convertToEmotion(chatData);

        emotionRepository.saveEmotion(emotion);

    }

    private Emotion convertToEmotion(ChatDTO chatData){
        Emotion entity = new Emotion();
        List<Integer> percent = (List<Integer>) chatData.getEmotions().values();
        entity.setHappiness(percent.get(0));
        entity.setAnxiety(percent.get(1));
        entity.setNeutral(percent.get(2));
        entity.setSadness(percent.get(3));
        entity.setAnger(percent.get(4));
        return entity;
    }
//
//
//    public Emotion getEmotionByChatId(Long chatId) {
//        return emotionRepository.findByChatId(chatId)
//                .orElseThrow(() -> new EntityNotFoundException("Emotion not found for chat id: " + chatId));
//    }
}
