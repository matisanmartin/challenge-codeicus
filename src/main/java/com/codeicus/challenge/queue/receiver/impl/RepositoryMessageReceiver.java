package com.codeicus.challenge.queue.receiver.impl;

import com.codeicus.challenge.model.TaskLog;
import com.codeicus.challenge.queue.receiver.MessageReceiver;
import com.codeicus.challenge.repository.TaskLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(value = "queue.rabbit.enabled", havingValue = "false")
public class RepositoryMessageReceiver implements MessageReceiver {

    @Autowired
    private TaskLogRepository taskLogRepository;

    @Override
    public void receiveTaskLogMessage(TaskLog taskLog) {
        taskLogRepository.save(taskLog);
    }
}
