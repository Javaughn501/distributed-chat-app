package com.chatapplication.messageservice.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final RedisTemplate<String, String> redisTemplate;

    @Value("${rabbitmq.queue.name}")
    private String APP_TOPIC;

    public void disconnect(Users user) {
        redisTemplate.delete(user.getUsername());
    }

    public void connect(Users users) {
        log.info("User connected {} and topic : {}", users.getUsername(), APP_TOPIC);
        redisTemplate.opsForValue().set(users.getUsername(), APP_TOPIC);
    }

}
