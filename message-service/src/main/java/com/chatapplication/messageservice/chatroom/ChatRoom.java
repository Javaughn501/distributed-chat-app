package com.chatapplication.messageservice.chatroom;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoom {

    private UUID id;
    private UUID chatId;
    private String senderId;
    private String recipientId;

}
