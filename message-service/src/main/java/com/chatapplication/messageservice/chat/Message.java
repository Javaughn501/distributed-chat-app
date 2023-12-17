package com.chatapplication.messageservice.chat;


import com.chatapplication.messageservice.chatroom.ChatRoom;
import com.chatapplication.messageservice.user.Users;
import jakarta.persistence.*;
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
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private ChatRoom chat;
    private String senderId;
    private String recipientId;
    private String content;
    private LocalTime timestamp;

}
