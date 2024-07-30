package com.feelinsight.feelinsight.controller;

import com.feelinsight.feelinsight.DTO.CalendarDTO.*;
import com.feelinsight.feelinsight.DTO.DiaryDTO.*;
import com.feelinsight.feelinsight.domain.Chat;
import com.feelinsight.feelinsight.domain.Diary;
import com.feelinsight.feelinsight.service.DiaryService;
import com.feelinsight.feelinsight.service.JwtUtility;
import com.feelinsight.feelinsight.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "일기 저장", description = "일기 내용, 일기 날짜, 일기 감정을 받아 저장")
    @PostMapping("/diary/save")
    public ResponseCalendar createDiary(@RequestHeader("Authorization") String token, @RequestBody RequestDiary requestDiary){
        String userToken = jwtUtility.bearerToken(token);
        String userId = userService.tokenToUser(userToken).getId();
        Chat chat = new Chat();
        chat.setMessage(requestDiary.getContent());
        Diary diary = diaryService.saveDiary(userId, chat, requestDiary.getDate(), requestDiary.getEmotion());
        return new ResponseCalendar(diary);
    }
    @Operation(summary = "일기 수정", description = "diary_id를 경로로 입력받고, 일기 내용을 입력 받아 수정")
    @PutMapping("/{diaryId}")
    public ResponseDiary updateDiary(@RequestHeader("Authorization") String token, @PathVariable Long diaryId, @RequestBody RequestDiary requestDiary){
        Diary diary = diaryService.updateDiary(diaryId, requestDiary.getContent(), token);
        return new ResponseDiary(diary);
    }
    @Operation(summary = "일기 삭제", description = "유저 토큰으로 일기 삭제")
    @DeleteMapping("/{diaryId}")
    public void deleteDiary(@RequestHeader("Authorization") String token, @PathVariable Long diaryId){
        diaryService.deleteDiary(diaryId, token);
    }
    @Operation(summary = "일기 조회", description = "유저일 경우 및 해당 날짜에 일기가 있을 경우 일기를 조회")
    @GetMapping("/diary")
    public ResponseCalendar getDiaryByDate(@RequestHeader("Authorization") String token, @RequestParam String date){
        String userToken = jwtUtility.bearerToken(token);
        String userId = userService.tokenToUser(userToken).getId();
        LocalDate localdate = LocalDate.parse(date);
        Diary diary = diaryService.findByUserIdAndDate(userId, localdate);
        return new ResponseCalendar(diary);
    }
    @Operation(summary = "사용자의 모든 일기 조회", description = "유저일 경우 유저의 모든 일기 반환")
    @GetMapping("/user")
    public List<Diary> getUserDiaries(@RequestHeader("Authorization") String token){
        String userToken = jwtUtility.bearerToken(token);
        String userId = userService.tokenToUser(userToken).getId();
        return diaryService.findUserDiary(userId);
    }
    @Operation(summary = "일자별 최상위 감정 조회", description = "유저일 경우 날짜별 최상위 감정 조회")
    @GetMapping("/diary/top-emotion")
    public TopEmotionResponse getTopEmotionBydate(@RequestHeader("Authorization") String token, @RequestParam String month){
        String userToken = jwtUtility.bearerToken(token);
        String userId = userService.tokenToUser(userToken).getId();
        LocalDate localMonth = LocalDate.parse(month+"-01");
        return diaryService.getTopEmotionByDate(userId, localMonth);
    }
}
