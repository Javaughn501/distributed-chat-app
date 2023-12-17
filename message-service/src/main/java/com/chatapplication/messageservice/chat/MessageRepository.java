package com.chatapplication.messageservice.chat;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    public List<Message> findAllByChatId(UUID chatId);
}
