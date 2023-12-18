package com.chatapplication.messageservice.chatroom;


import com.chatapplication.messageservice.chat.ChatMessage;
import com.chatapplication.messageservice.chat.Message;
import com.chatapplication.messageservice.user.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String user1;
    private String user2;

    @OneToMany
    private List<Message> messages;
}
