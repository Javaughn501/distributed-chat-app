package com.chatapplication.messageservice.user;


import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @MessageMapping("/user/disconnect")
    @SendTo("/user/topic")
    public void disconnect(@Payload Users user) {
        userService.disconnect(user);
    }


}
