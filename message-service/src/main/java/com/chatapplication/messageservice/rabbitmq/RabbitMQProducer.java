package com.chatapplication.messageservice.rabbitmq;

import com.chatapplication.messageservice.chat.ChatMessage;
import com.chatapplication.messageservice.user.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMQProducer {



    @Value("${rabbitmq.routing.key}")
    private String routingKey;


    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(ChatMessage message, String exchange){
        log.info("Sending message to queue");
        rabbitTemplate.convertAndSend(exchange, message);
    }

}