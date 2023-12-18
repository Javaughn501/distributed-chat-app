package com.chatapplication.messageservice.chatroom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;


public interface ChatRoomRepository extends JpaRepository<ChatRoom, UUID> {
    List<ChatRoom> findAllByUser1AndUser2(String user1, String user2);
    @Query("SELECT c FROM ChatRoom c WHERE c.user1 = :username OR c.user2 = :username")
    List<ChatRoom> findAllByUser1OrUser2(String username);

}
