package com.chatapplication.messageservice.chatroom;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/chatroom")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @PostMapping
    public ChatRoomDTO createChat(@RequestBody ChatRoomDTO chatRoomDTO) {
        return chatRoomService.createChat(chatRoomDTO);
    }

    @GetMapping("/{username}")
    public List<ChatRoomDTO> getAllChats(@PathVariable String username) {
        return chatRoomService.getAllChats(username);
    }




}
