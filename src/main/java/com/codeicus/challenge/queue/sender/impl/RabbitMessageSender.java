package com.codeicus.challenge.queue.sender.impl;

import com.codeicus.challenge.model.TaskLog;
import com.codeicus.challenge.queue.sender.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(value = "queue.rabbit.enabled", havingValue = "true")
public class RabbitMessageSender implements MessageSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMessageSender.class);
    private static final String SENDING_MESSAGE_TO_QUEUE = "Sending message to queue {}";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    @Override
    public void sendTaskLogMessage(TaskLog taskLog) {
        LOGGER.info(SENDING_MESSAGE_TO_QUEUE, taskLog);
        rabbitTemplate.convertAndSend(queue.getName(), taskLog);
    }
}
