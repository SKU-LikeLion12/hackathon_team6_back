package com.feelinsight.feelinsight.controller;

import com.feelinsight.feelinsight.DTO.ChatDTO;
import com.feelinsight.feelinsight.service.ChatService;
import com.feelinsight.feelinsight.service.EmotionService;
import com.feelinsight.feelinsight.service.SituationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final SituationService situationService;
    private final EmotionService emotionService;

    @PostMapping("/api/chat")
    public ResponseEntity<String> receiveChatData(@RequestBody ChatDTO chatData) {
        situationService.processSituationData(chatData);
        chatService.processChatData(chatData);
        emotionService.processEmotionData(chatData);
        return new ResponseEntity<>("대화 데이터가 성공적으로 처리되어 저장되었습니다.", HttpStatus.OK);
    }
}