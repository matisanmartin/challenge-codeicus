package com.codeicus.challenge.queue.receiver.impl;

import com.codeicus.challenge.model.TaskLog;
import com.codeicus.challenge.queue.receiver.MessageReceiver;
import com.codeicus.challenge.repository.TaskLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(value = "queue.rabbit.enabled", havingValue = "false")
public class RepositoryMessageReceiver implements MessageReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryMessageReceiver.class);
    private static final String REPOSITORY_RECEIVER_PERSISTING_MESSAGE = "Repository receiver persisting message {}";

    @Autowired
    private TaskLogRepository taskLogRepository;

    @Override
    public void receiveTaskLogMessage(TaskLog taskLog) {
        LOGGER.info(REPOSITORY_RECEIVER_PERSISTING_MESSAGE, taskLog);
        taskLogRepository.save(taskLog);
    }
}
