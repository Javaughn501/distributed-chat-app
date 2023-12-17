package com.chatapplication.messageservice.user;


import com.chatapplication.messageservice.rabbitmq.RabbitMQProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final RabbitMQProducer mqProducer;


    @MessageMapping("/user/disconnect")
    public void disconnect(@Payload Users user) {
        userService.disconnect(user);
    }

    @MessageMapping("/user/connect")
    public void connect(@Payload Users users) {
        userService.connect(users);
    }



}
