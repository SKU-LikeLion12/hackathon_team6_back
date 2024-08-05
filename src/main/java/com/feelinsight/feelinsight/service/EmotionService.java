package com.feelinsight.feelinsight.service;


import com.feelinsight.feelinsight.DTO.ChatDTO;
import com.feelinsight.feelinsight.domain.Diary;
import com.feelinsight.feelinsight.domain.Emotion;
import com.feelinsight.feelinsight.domain.User;
import com.feelinsight.feelinsight.exception.EmotionNotFoundException;
import com.feelinsight.feelinsight.exception.IdNotFoundException;
import com.feelinsight.feelinsight.repository.EmotionRepository;

import java.time.LocalDate;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmotionService {
    private final EmotionRepository emotionRepository;
    private final UserService userService;
    private final DiaryService diaryService;

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

    public Emotion findByUserAndDate(long userId, LocalDate date){
        User user=userService.findByUserId(userId);
        if(user==null){
            throw new IdNotFoundException("해당 유저를 찾을 수 없습니다.");
        }
        Diary diary=diaryService.findByUserIdAndDate(user.getId(), date);
        if(date==diary.getDate()){
            Emotion emotion=diary.getEmotion();
            return emotion;
        }
        else{
            throw new EmotionNotFoundException("해당 감정을 찾을 수 없습니다.");
        }
    }

    public Emotion findByUserEmotion(long userId){
        User user=userService.findByUserId(userId);
        if(user==null){
            throw new IdNotFoundException("해당 유저를 찾을 수 없습니다.");
        }
        return emotionRepository.findByUserIdEmotion(userId);
    }
}
