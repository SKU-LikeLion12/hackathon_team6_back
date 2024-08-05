package com.feelinsight.feelinsight.service;

import com.feelinsight.feelinsight.DTO.ChatDTO;
import com.feelinsight.feelinsight.domain.Chat;
import com.feelinsight.feelinsight.exception.ChatNotFoundException;
import com.feelinsight.feelinsight.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatService {
    private final ChatRepository chatRepository;
    @Value("${django.server.url}")
    private String djangoServerUrl;

    @Transactional
    public void processChatData(ChatDTO.ChatTransfer chatTransfer) {
        try {
            Chat chat = convertToChat(chatTransfer);
            // 데이터베이스에 저장
            chatRepository.saveNewChat(chat);
        } catch (Exception e) {
            throw new RuntimeException("채팅 데이터를 처리하는 중 오류가 발생했습니다.", e);
        }
    }

    private Chat convertToChat(ChatDTO.ChatTransfer chatTransfer) {
        Chat entity = new Chat();
        entity.setChatId(chatTransfer.getUserId());
        entity.setMessage(chatTransfer.getMessage());
        entity.setStartTime(chatTransfer.getStartTime());
        entity.setEndTime(chatTransfer.getEndTime());
        return entity;
    }

    public Chat findByChatId(Long chatId){
        Chat chat = chatRepository.findByChatId(chatId);
        if(chat==null){
            throw new ChatNotFoundException("해당 ID의 대화 데이터를 찾을 수 없습니다.");
        }
        return chat;
    }

//    public void sendFiletoDjangoServer(MultipartFile file) throws IOException{
//        WebClient webClient=WebClient.builder()
//                .baseUrl(djangoServerUrl)
//                .build();
//
//        Multi
//    }
}
