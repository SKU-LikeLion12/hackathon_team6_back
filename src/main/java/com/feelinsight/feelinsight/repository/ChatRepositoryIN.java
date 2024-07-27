package com.feelinsight.feelinsight.repository;

import com.feelinsight.feelinsight.domain.Chat;
import com.feelinsight.feelinsight.domain.User;
import java.util.List;

public interface ChatRepositoryIN{
    List<Chat> findByUserAndIsRefined(User user, boolean isRefined);

    Chat saveNewChat(Chat chat);

    Chat findById(Long id);

}
