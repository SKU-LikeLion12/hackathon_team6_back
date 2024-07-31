package com.feelinsight.feelinsight.controller;

import com.feelinsight.feelinsight.DTO.CalendarDTO.*;
import com.feelinsight.feelinsight.DTO.DiaryDTO.*;
import com.feelinsight.feelinsight.domain.Chat;
import com.feelinsight.feelinsight.domain.Diary;
import com.feelinsight.feelinsight.exception.DiaryNotFoundException;
import com.feelinsight.feelinsight.exception.InvalidTokenException;
import com.feelinsight.feelinsight.service.DiaryService;
import com.feelinsight.feelinsight.service.JwtUtility;
import com.feelinsight.feelinsight.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseCalendar> createDiary(@RequestHeader("Authorization") String token, @RequestBody RequestDiary requestDiary) {
        try {
            String userToken = jwtUtility.bearerToken(token);
            String userId = userService.tokenToUser(userToken).getId();
            Chat chat = new Chat();
            chat.setMessage(requestDiary.getContent());
            Diary diary = diaryService.saveDiary(userId, chat, requestDiary.getDate(), requestDiary.getEmotion());
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseCalendar(diary));
        } catch (InvalidTokenException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{diaryId}")
    public ResponseEntity<ResponseDiary> updateDiary(@RequestHeader("Authorization") String token, @PathVariable Long diaryId, @RequestBody RequestDiary requestDiary){
        try {
            Diary diary = diaryService.updateDiary(diaryId, requestDiary.getContent(), token);
            return ResponseEntity.ok(new ResponseDiary(diary));
        } catch (InvalidTokenException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (DiaryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{diaryId}")
    public ResponseEntity<Void> deleteDiary(@RequestHeader("Authorization") String token, @PathVariable Long diaryId){
        try {
            diaryService.deleteDiary(diaryId, token);
            return ResponseEntity.noContent().build();
        } catch (InvalidTokenException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (DiaryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/diary")
    public ResponseEntity<ResponseCalendar> getDiaryByDate(@RequestHeader("Authorization") String token, @RequestParam String date){
        try {
            String userToken = jwtUtility.bearerToken(token);
            String userId = userService.tokenToUser(userToken).getId();
            LocalDate localDate = LocalDate.parse(date);
            Diary diary = diaryService.findByUserIdAndDate(userId, localDate);
            return ResponseEntity.ok(new ResponseCalendar(diary));
        } catch (InvalidTokenException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (DiaryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<List<Diary>> getUserDiaries(@RequestHeader("Authorization") String token){
        try {
            String userToken = jwtUtility.bearerToken(token);
            String userId = userService.tokenToUser(userToken).getId();
            List<Diary> diaries = diaryService.findUserDiary(userId);
            return ResponseEntity.ok(diaries);
        } catch (InvalidTokenException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping("/diary/top-emotion")
    public ResponseEntity<TopEmotionResponse> getTopEmotionByDate(@RequestHeader("Authorization") String token, @RequestParam String month){
        try {
            String userToken = jwtUtility.bearerToken(token);
            String userId = userService.tokenToUser(userToken).getId();
            LocalDate localMonth = LocalDate.parse(month + "-01");
            TopEmotionResponse response = diaryService.getTopEmotionByDate(userId, localMonth);
            return ResponseEntity.ok(response);
        } catch (InvalidTokenException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
