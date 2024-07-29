package com.feelinsight.feelinsight.repository;

import com.feelinsight.feelinsight.domain.Chat;
import com.feelinsight.feelinsight.domain.User;
import java.util.List;

public interface ChatRepositoryIN{
    Chat saveNewChat(Chat chat);

    Chat findByChatId(Long ChatId);

}
