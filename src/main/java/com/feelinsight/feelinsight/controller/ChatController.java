package com.feelinsight.feelinsight.controller;

import com.feelinsight.feelinsight.DTO.ChatDTO;
import com.feelinsight.feelinsight.DTO.ChatDTO.ChatResponse;
import com.feelinsight.feelinsight.domain.Chat;
import com.feelinsight.feelinsight.exception.ChatNotFoundException;
import com.feelinsight.feelinsight.exception.InvalidTokenException;
import com.feelinsight.feelinsight.service.*;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final SituationService situationService;
    private final EmotionService emotionService;

    private final JwtUtility jwtUtility;
    private final DiaryService diaryService;

    @PostMapping("/upload-audio")
    public ResponseEntity<String> handleFileUpload(@RequestHeader("Authorization") String token,
                                                   @RequestParam("file") MultipartFile file) {
        Claims claims;

        try {
            claims = jwtUtility.validateToken(token);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }

        String userId = claims.getSubject();

        try {
            // MultipartFile을 바이트 배열로 변환
            byte[] fileBytes = file.getBytes();
            chatService.sendFiletoDjangoServer(fileBytes, userId);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file to Django server.");
        }

        return ResponseEntity.ok("File uploaded successfully");
    }


    @Operation(summary = "대화,상황,감정 저장", description = "GPT를 통해 분석된 내용을 가져와서 저장",
            responses = {@ApiResponse(responseCode = "200", description = "성공")})
    @PostMapping("/api/chat")
    public ResponseEntity<String> receiveChatData(@RequestBody ChatDTO.ChatTransfer chatTransfer) {
        situationService.processSituationData(chatTransfer);
        chatService.processChatData(chatTransfer);
        emotionService.processEmotionData(chatTransfer);
        return new ResponseEntity<>("대화 데이터가 성공적으로 처리되어 저장되었습니다.", HttpStatus.OK);

    }

    @Operation(summary = "대화내용 찾기", description = "경로의 chat의 Id로 chat의 정보 찾기",
            responses = {@ApiResponse(responseCode = "200", description = "성공"),
                        @ApiResponse(responseCode = "404", description = "대화데이터 를 찾을 수 없음")})
    @GetMapping("/chat/{chatId}")
    public ResponseEntity<ChatResponse> getChat(@Parameter(description = "chat ID", example = "test_id") @PathVariable("chatId") Long chatId) {
        try {
            Chat chat = chatService.findByChatId(chatId);
            ChatResponse chatResponse = new ChatResponse(chat);
            return ResponseEntity.ok(chatResponse);
        } catch (ChatNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}