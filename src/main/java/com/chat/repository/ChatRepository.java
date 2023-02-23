package com.chat.repository;

import com.chat.entity.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    void deleteById(Long id);

    Optional<Chat> findByName(String name);
}
