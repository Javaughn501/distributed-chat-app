package com.chatapplication.messageservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String RHOST;

    @Value("${spring.redis.port}")
    private Integer RPORT;

    @Bean
    public LettuceConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(RHOST);
        config.setPort(RPORT);
        return new LettuceConnectionFactory(config);
    }

}
