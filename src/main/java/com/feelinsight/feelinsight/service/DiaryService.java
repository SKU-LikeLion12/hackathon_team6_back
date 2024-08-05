package com.feelinsight.feelinsight.service;

import com.feelinsight.feelinsight.DTO.CalendarDTO.*;
import com.feelinsight.feelinsight.domain.Chat;
import com.feelinsight.feelinsight.domain.Diary;
import com.feelinsight.feelinsight.domain.Emotion;
import com.feelinsight.feelinsight.domain.User;
import com.feelinsight.feelinsight.exception.DiaryNotFoundException;
import com.feelinsight.feelinsight.exception.IdNotFoundException;
import com.feelinsight.feelinsight.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final UserService userService;

    @Transactional
    public Diary saveDiary(String id, Chat chat, LocalDate date, Emotion emotion){
        User user =userService.findById(id);
        Diary diary=new Diary(user, date, emotion, chat);
        diaryRepository.saveDiary(diary);
        return diary;
    }

    @Transactional
    public Diary updateDiary(String content, String token, LocalDate date){
        String userId=userService.tokenToUser(token).getId();
        Diary diary=findByUserIdAndDate(userId, date);
        if(diary==null){
            throw new DiaryNotFoundException("일기를 찾을 수 없습니다.");
        }
        User user= userService.tokenToUser(token);
        if(user==diary.getUser()){
            diary.updateDiary(content);
        }
        return diary;
    }

    @Transactional
    public void deleteDiary(Long diaryId, String token){
        Diary diary = diaryRepository.findByDiaryId(diaryId);
        if(diary==null){
            throw new DiaryNotFoundException("일기를 찾을 수 없습니다.");
        }
        User user=userService.tokenToUser(token);
        if(user==diary.getUser()){
            diaryRepository.deleteDiary(diary);
        }
    }

    public Diary findByUserIdAndDate(String id, LocalDate date){
        User user=userService.findById(id);
        Diary diary=diaryRepository.findByuserIdAndDate(user.getUserId(), date);
        if(diary==null){
            throw new DiaryNotFoundException("일기를 찾을 수 없습니다.");
        }
        return diary;
    }

    public Diary findDiary(Long diaryId){
        Diary diary=diaryRepository.findByDiaryId(diaryId);
        if(diary==null){
            throw new DiaryNotFoundException("일기를 찾을 수 없습니다.");
        }
        return diary;
    }

    public List<Diary> findUserDiary(String id){
        User user = userService.findById(id);
        return diaryRepository.findByUserAll(user.getUserId());
    }

    public TopEmotionResponse getTopEmotionByDate(String userId, LocalDate month) {
        User user = userService.findById(userId);
        if(user==null){
            throw new IdNotFoundException("사용자를 찾을 수 없습니다.");
        }
        List<Diary> diaries = diaryRepository.findByUserIdAndMonth(user.getUserId(), month);
        Map<LocalDate, String> topEmotions = new HashMap<>();

        for (Diary diary : diaries) {
            Emotion emotion = diary.getEmotion();
            Map<String, Integer> emotionMap = new HashMap<>();
            emotionMap.put("happiness", emotion.getHappiness());
            emotionMap.put("anxiety", emotion.getAnxiety());
            emotionMap.put("neutral", emotion.getNeutral());
            emotionMap.put("sadness", emotion.getSadness());
            emotionMap.put("anger", emotion.getAnger());

            String topEmotion = emotionMap.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .get()
                    .getKey();

            topEmotions.put(diary.getDate(), topEmotion);
        }

        return new TopEmotionResponse(topEmotions);
    }
}
