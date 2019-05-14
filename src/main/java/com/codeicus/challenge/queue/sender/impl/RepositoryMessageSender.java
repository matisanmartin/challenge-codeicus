package com.codeicus.challenge.queue.sender.impl;

import com.codeicus.challenge.model.TaskLog;
import com.codeicus.challenge.queue.receiver.MessageReceiver;
import com.codeicus.challenge.queue.sender.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(value = "queue.rabbit.enabled", havingValue = "false")
public class RepositoryMessageSender implements MessageSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryMessageSender.class);
    private static final String CREATING_RECORD_ON_TASK_LOG_VIA_REPOSITORY = "Creating record on TaskLog {} via repository";

    @Autowired
    private MessageReceiver messageReceiver;

    @Override
    public void sendTaskLogMessage(TaskLog taskLog) {
        LOGGER.info(CREATING_RECORD_ON_TASK_LOG_VIA_REPOSITORY, taskLog);
        messageReceiver.receiveTaskLogMessage(taskLog);
    }
}
