package com.chat.repository;

import com.chat.entity.chat.UserChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserChatRepository extends JpaRepository<UserChat, Long> {

    List<UserChat> findAllByChatId(Long id);
}
