package com.chatapplication.messageservice.config;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;


@Configuration
@AllArgsConstructor
@NoArgsConstructor
public class NameConfig {

    @Value("${spring.application.name}")
    private String APP_NAME;

    @Bean
    public String appTopic() {
        return APP_NAME + "_" + UUID.randomUUID().toString();
    }

}
