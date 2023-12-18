package com.chatapplication.messageservice.feign;


import com.chatapplication.messageservice.user.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", contextId = "user")
public interface UserClient {

    @GetMapping("/api/v1/member/{username}")
    Users getUser(@PathVariable String username);

}
