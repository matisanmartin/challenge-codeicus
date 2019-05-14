package com.codeicus.challenge.queue.receiver.impl;

import com.codeicus.challenge.model.TaskLog;
import com.codeicus.challenge.queue.receiver.MessageReceiver;
import com.codeicus.challenge.repository.TaskLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@ConditionalOnProperty(value = "queue.rabbit.enabled", havingValue = "true")
public class RabbitMessageReceiver implements MessageReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMessageReceiver.class);
    @Autowired
    private TaskLogRepository taskLogRepository;

    @RabbitListener(queues = "taskLogQueue")
    @Transactional
    @Override
    public void receiveTaskLogMessage(TaskLog taskLog) {
        try {
            taskLogRepository.save(taskLog);
        } catch(Exception e) {
            LOGGER.error("Error saving entity {} to database", taskLog);
            LOGGER.error("Exception: ", e);
            //TODO implementar politica de retry para que el mensaje vuelva al queue si hay una falla al persistir
        }
    }
}
