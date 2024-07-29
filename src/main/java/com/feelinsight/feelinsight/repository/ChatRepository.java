package com.feelinsight.feelinsight.repository;

import com.feelinsight.feelinsight.domain.Chat;
import com.feelinsight.feelinsight.domain.User;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChatRepository implements ChatRepositoryIN{
    private final EntityManager em;
    @Override
    public Chat saveNewChat(Chat chat) {
        em.persist(chat);
        return chat;
    }

    @Override
    public Chat findByChatId(Long ChatId){
        return em.find(Chat.class, ChatId);
    }
}
