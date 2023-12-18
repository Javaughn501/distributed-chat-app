package com.chatapplication.messageservice.chatroom;


import com.chatapplication.messageservice.exception.ResourceNotFoundException;
import com.chatapplication.messageservice.feign.UserClient;
import com.chatapplication.messageservice.user.Users;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserClient userClient;


    public ChatRoomDTO createChat(ChatRoomDTO chatRoomDTO) {

        Users sender;
        Users recipient;

        try {
            sender = userClient.getUser(chatRoomDTO.getSenderId());
            recipient = userClient.getUser(chatRoomDTO.getRecipientId());
        } catch (FeignException.NotFound e) {
            log.info("Sender or Recipient Not Found");
            throw new ResourceNotFoundException("Sender / Recipient not found");
        }

        ChatRoom senderRoom = ChatRoom.builder()
                .senderId(sender.getUsername())
                .recipientId(recipient.getUsername())
                .build();

        ChatRoom receiverRoom = ChatRoom.builder()
                .senderId(recipient.getUsername())
                .recipientId(sender.getUsername())
                .build();

        ChatRoom savedRoom = chatRoomRepository.save(senderRoom);
        chatRoomRepository.save(receiverRoom);

        //TODO: Create Chat - Check if both recepient exists
        //TODO: Create chat Room for othe rperson too


        return ChatRoomDTO.builder()
                .id(savedRoom.getId())
                .senderId(savedRoom.getSenderId())
                .recipientId(savedRoom.getRecipientId())
                .build();
    }

    public List<ChatRoomDTO> getAllChats(String username) {
        return chatRoomRepository.findAllBySenderId(username)
                .stream()
                .map(room -> ChatRoomDTO.builder()
                        .id(room.getId())
                        .senderId(room.getSenderId())
                        .recipientId(room.getRecipientId())
                        .build()
                )
                .toList();
    }
}
