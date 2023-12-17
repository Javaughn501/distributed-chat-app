package com.chatapplication.messageservice.chatroom;

import com.chatapplication.messageservice.user.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Data
@AllArgsConstructor
@Builder
public class ChatRoomDTO {
    private UUID id;
    private String senderId;
    private String recipientId;
}
