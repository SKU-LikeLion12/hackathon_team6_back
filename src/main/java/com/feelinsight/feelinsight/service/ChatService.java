package com.feelinsight.feelinsight.service;

import com.feelinsight.feelinsight.DTO.ChatDTO;
import com.feelinsight.feelinsight.domain.Chat;
import com.feelinsight.feelinsight.domain.Emotion;
import com.feelinsight.feelinsight.domain.Situation;
import com.feelinsight.feelinsight.domain.User;
import com.feelinsight.feelinsight.exception.IdNotFoundException;
import com.feelinsight.feelinsight.repository.ChatRepository;
import com.feelinsight.feelinsight.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.text.html.parser.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatService {
    private final ChatRepository chatRepository;

    public void processChatData(ChatDTO chatData) {
        Chat chat = convertToChat(chatData);
        // 데이터베이스에 저장
        chatRepository.saveNewChat(chat);
    }

    private Chat convertToChat(ChatDTO chatData) {
        Chat entity = new Chat();
        entity.setChatId(chatData.getUserId());
        entity.setMessage(chatData.getMessage());
        entity.setStartTime(chatData.getStartTime());
        entity.setEndTime(chatData.getEndTime());
        return entity;
    }
}
