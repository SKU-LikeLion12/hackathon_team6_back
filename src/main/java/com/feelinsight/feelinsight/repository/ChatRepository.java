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
    public List<Chat> findByUserAndIsRefined(User user, boolean isRefined) {
        return null;
    }

    @Override
    public Chat saveNewChat(Chat chat) {
        em.persist(chat);
        return chat;
    }

    @Override
    public Chat findById(Long id){
        return em.find(Chat.class, id);
    }
}
