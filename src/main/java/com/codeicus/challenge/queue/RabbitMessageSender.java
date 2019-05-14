package com.codeicus.challenge.queue;

import com.codeicus.challenge.model.TaskLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(value = "queue.rabbit.enabled", havingValue = "true")
public class RabbitMessageSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMessageSender.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    public void sendTaskLogMessage(TaskLog taskLog) {
        rabbitTemplate.convertAndSend(queue.getName(), taskLog);
    }
}
