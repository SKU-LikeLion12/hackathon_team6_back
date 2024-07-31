package com.feelinsight.feelinsight.service;

import com.feelinsight.feelinsight.DTO.ChatDTO;
import com.feelinsight.feelinsight.domain.Chat;
import com.feelinsight.feelinsight.exception.ChatNotFoundException;
import com.feelinsight.feelinsight.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatService {
    private final ChatRepository chatRepository;

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
}
