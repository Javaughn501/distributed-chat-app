package com.chatapplication.messageservice.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Javaughn Stephenson
 * @since 12/12/2023
 */

@Service
@RequiredArgsConstructor
public class UserService {

    public void disconnect(Users user) {
        //TODO: remove user from redis
    }

}
