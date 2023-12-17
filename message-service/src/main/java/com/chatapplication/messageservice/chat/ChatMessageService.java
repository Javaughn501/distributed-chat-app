package com.chatapplication.messageservice.chat;


import com.chatapplication.messageservice.chatroom.ChatRoom;
import com.chatapplication.messageservice.chatroom.ChatRoomRepository;
import com.chatapplication.messageservice.rabbitmq.RabbitMQProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatMessageService {

    @Value("${rabbitmq.queue.name}")
    private String APP_TOPIC;

    private final RedisTemplate<String, String> redisTemplate;
    private final RabbitMQProducer rabbitMQProducer;
    private final SimpMessagingTemplate template;
    private final MessageRepository messageRepository;


    private ChatMessage saveMessage(ChatMessage message) {
        //TODO: save chat message
        Message messageEntity = Message.builder()
                .chat(ChatRoom.builder().id(message.getChat()).build())
                .senderId(message.getSenderId())
                .recipientId(message.getRecipientId())
                .content(message.getContent())
                .timestamp(LocalTime.now())
                .build();

        Message savedMessage = messageRepository.save(messageEntity);
        log.info("Message Saved {}", savedMessage.getChat().getId());
        return message;
    }

    public void processMessage(ChatMessage chatMessage) {

        saveMessage(chatMessage);

        String recipient = redisTemplate.opsForValue().get(chatMessage.getRecipientId());

        if (recipient != null || !recipient.equals(APP_TOPIC)) {
            rabbitMQProducer.sendMessage(chatMessage, recipient);
        }
        else {
            template.convertAndSendToUser(
                    chatMessage.getRecipientId(),
                    "/queue/messages",
                    chatMessage
            );
        }
    }

    public List<ChatMessage> getMessagesForChat (UUID chatId) {
        return messageRepository.findAllByChatId(chatId)
                .stream()
                .map(message -> ChatMessage
                        .builder()
                        .id(message.getId())
                        .chat(message.getChat().getId())
                        .senderId(message.getSenderId())
                        .recipientId(message.getRecipientId())
                        .content(message.getContent())
                        .timestamp(message.getTimestamp())
                        .build()
                )
                .toList();
    }

}
