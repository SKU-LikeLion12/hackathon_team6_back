package com.feelinsight.feelinsight.controller;

import com.feelinsight.feelinsight.DTO.CalendarDTO;
import com.feelinsight.feelinsight.DTO.CalendarDTO.*;
import com.feelinsight.feelinsight.DTO.DiaryDTO.*;
import com.feelinsight.feelinsight.domain.Chat;
import com.feelinsight.feelinsight.domain.Diary;
import com.feelinsight.feelinsight.service.DiaryService;
import com.feelinsight.feelinsight.service.JwtUtility;
import com.feelinsight.feelinsight.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;
    private final JwtUtility jwtUtility;
    private final UserService userService;

    @PostMapping("/diary/save")
    public ResponseCalendar createDiary(@RequestHeader("Authorization") String token, @RequestBody RequestDiary requestDiary){
        String userToken =jwtUtility.bearerToken(token);
        String userId=userService.tokenToUser(token).getId();
        Chat chat=new Chat();
        chat.setMessage(requestDiary.getContent());
        Diary diary=diaryService.saveDiary(userId, chat, requestDiary.getDate(), requestDiary.getEmotion());
        return new ResponseCalendar(diary);
    }

    @PutMapping("/{diaryId}")
    public ResponseDiary updateDiary(@RequestHeader("Authorization") String token, @PathVariable Long diaryId, @RequestBody RequestDiary requestDiary){
        Diary diary=diaryService.updateDiary(diaryId, requestDiary.getContent(), token);
        return new ResponseDiary(diary);
    }

    @DeleteMapping("/{diaryId}")
    public void deleteDiary(@RequestHeader("Authorization") String token, @PathVariable Long diaryId){
        diaryService.deleteDiary(diaryId, token);
    }

    @GetMapping("/diary")
    public ResponseCalendar getDiaryByDate(@RequestHeader("Authorization") String token, @RequestParam String date){
        String userToken=jwtUtility.bearerToken(token);
        String userId=userService.tokenToUser(userToken).getId();
        LocalDate localdate=LocalDate.parse(date);
        Diary diary=diaryService.findByUserIdAndDate(userId, localdate);
        return new ResponseCalendar(diary);
    }

    @GetMapping("/user")
    public List<Diary> getUserDiaries(@RequestHeader("Authorization") String token){
        String userToken=jwtUtility.bearerToken(token);
        String userId=userService.tokenToUser(userToken).getId();
        return diaryService.findUserDiary(userId);
    }

    @GetMapping("/diary/top-emotion")
    public TopEmotionResponse getTopEmotionBydate(@RequestHeader("Authorization") String token, @RequestParam String month){
        String userToken= jwtUtility.bearerToken(token);
        String userId=userService.tokenToUser(userToken).getId();
        LocalDate localMonth=LocalDate.parse(month+"-01");
        return diaryService.getTopEmotionByDate(userId, localMonth);
    }
}
