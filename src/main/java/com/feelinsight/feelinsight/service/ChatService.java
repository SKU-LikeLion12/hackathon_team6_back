package com.feelinsight.feelinsight.service;

import com.feelinsight.feelinsight.DTO.ChatDTO;
import com.feelinsight.feelinsight.domain.Chat;
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
        Chat chat = convertToChat(chatTransfer);
        // 데이터베이스에 저장
        chatRepository.saveNewChat(chat);
    }

    private Chat convertToChat(ChatDTO.ChatTransfer chatTransfer) {
        Chat entity = new Chat();
        entity.setChatId(chatTransfer.getUserId());
        entity.setMessage(chatTransfer.getMessage());
        entity.setStartTime(chatTransfer.getStartTime());
        entity.setEndTime(chatTransfer.getEndTime());
        return entity;
    }

    public Chat findByChatId(Long chatId){return chatRepository.findByChatId(chatId);}
}
