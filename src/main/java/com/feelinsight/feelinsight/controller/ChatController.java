package com.feelinsight.feelinsight.controller;

import com.feelinsight.feelinsight.DTO.ChatDTO;
import com.feelinsight.feelinsight.DTO.ChatDTO.ChatResponse;
import com.feelinsight.feelinsight.domain.Chat;
import com.feelinsight.feelinsight.exception.ChatNotFoundException;
import com.feelinsight.feelinsight.service.ChatService;
import com.feelinsight.feelinsight.service.EmotionService;
import com.feelinsight.feelinsight.service.SituationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final SituationService situationService;
    private final EmotionService emotionService;

    @Operation(summary = "대화,상황,감정 저장", description = "GPT를 통해 분석된 내용을 가져와서 저장")
    @PostMapping("/api/chat")
    public ResponseEntity<String> receiveChatData(@RequestBody ChatDTO.ChatTransfer chatTransfer) {
        try{
            situationService.processSituationData(chatTransfer);
            chatService.processChatData(chatTransfer);
            emotionService.processEmotionData(chatTransfer);
            return new ResponseEntity<>("대화 데이터가 성공적으로 처리되어 저장되었습니다.", HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("대화데이터를 처리하는 중 오류가 발생했습니다.",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Operation(summary = "대화내용 찾기", description = "경로의 chat의 Id로 chat의 정보 찾기")
    @GetMapping("/chat/{chatId}")

    public ResponseEntity<ChatResponse> getChat(@Parameter(description = "chat ID", example = "test_id") @PathVariable("chatId") Long chatId) {
        try {
            Chat chat = chatService.findByChatId(chatId);
            ChatResponse chatResponse = new ChatResponse(chat);
            return ResponseEntity.ok(chatResponse);
        } catch (ChatNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}