package com.chatapplication.messageservice.chat;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {

    private UUID id;
    private UUID chatId;
    private String senderId;
    private String recipientId;
    private String content;
    private LocalTime timestamp;

}
