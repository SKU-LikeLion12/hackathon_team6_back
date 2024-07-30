package com.feelinsight.feelinsight.repository;

import com.feelinsight.feelinsight.domain.Chat;



public interface ChatRepositoryIN{
    Chat saveNewChat(Chat chat);

    Chat findByChatId(Long ChatId);

}
