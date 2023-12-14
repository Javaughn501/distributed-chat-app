package com.chatapplication.userservice.exception;

/**
 * @author Javaughn Stephenson
 * @Since 03/12/2023
 */

public class ConlfictException extends RuntimeException {
    public ConlfictException() {
        super();
    }

    public ConlfictException(String message) {
        super(message);
    }
}
