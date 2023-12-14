package com.chatapplication.messageservice.chat;


import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final SimpMessagingTemplate template;

    public ChatMessage save(ChatMessage message) {
        //TODO: save chat message
        return message;
    }

    public void processMessage(ChatMessage chatMessage) {
        template.convertAndSendToUser(
                chatMessage.getRecipientId(),
                "/queue/messages",
                chatMessage
        );
    }

}
