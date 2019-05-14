package com.codeicus.challenge.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfiguration {

    @Value("${queue.name.taskLog}")
    private String taskLogQueueName;

    @Bean
    public Queue queue() {
        return new Queue(taskLogQueueName);
    }

}
