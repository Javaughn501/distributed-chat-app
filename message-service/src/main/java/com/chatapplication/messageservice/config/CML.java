package com.chatapplication.messageservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * @author Javaughn Stephenson
 * @since 18/12/2023
 */

@Configuration
@Slf4j
public class CML implements CommandLineRunner {

    @Autowired
    private RedisConnectionFactory factory;

    @Value("${spring.redis.host}")
    private String red_host;

    @Override
    public void run(String... args) throws Exception {
        log.info("----------------------------");
        log.info("----------------------------");
        log.info("REDIS HOST IS : {}", red_host);
        log.info("----------------------------");
        log.info("----------------------------");
        log.info("REDIS CONNECTION FACTORY = {}", factory);
        log.info("----------------------------");
        log.info("----------------------------");
    }
}
