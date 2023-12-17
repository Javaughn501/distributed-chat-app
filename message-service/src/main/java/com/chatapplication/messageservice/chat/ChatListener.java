package com.chatapplication.messageservice.chat;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatListener {

    private final SimpMessagingTemplate template;

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void receiveMessage(ChatMessage message) {
        log.info("Received Message from {} to {} in chat {}", message.getSenderId(), message.getRecipientId(), message.getChat());

        template.convertAndSendToUser(
                message.getRecipientId(),
                "/queue/messages",
                message
        );
    }

}
