package com.chatapplication.messageservice.rabbitmq;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RabbitMqConfig {

        @Value("${rabbitmq.queue.name}")
        private String queue;

        @Value("${rabbitmq.exchange.name}")
        private String exchange;

        @Value("${rabbitmq.routing.key}")
        private String routingKey;

        // spring bean for rabbitmq queue
        @Bean
        public Queue queue(){
            return new Queue(queue, false);
        }

        @Bean
        public Jackson2JsonMessageConverter converter() {
                return new Jackson2JsonMessageConverter();
        }

        @Bean
        public RabbitTemplate jsonRabbitTemplate(ConnectionFactory connectionFactory, ObjectMapper mapper) {
                final var jsonRabbitTemplate = new RabbitTemplate(connectionFactory);
                jsonRabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter(mapper));
                return jsonRabbitTemplate;
        }
}
