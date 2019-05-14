package com.codeicus.challenge.queue.sender.impl;

import com.codeicus.challenge.model.TaskLog;
import com.codeicus.challenge.queue.receiver.MessageReceiver;
import com.codeicus.challenge.queue.sender.MessageSender;
import com.codeicus.challenge.repository.TaskLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(value = "queue.rabbit.enabled", havingValue = "false")
public class RepositoryMessageSender implements MessageSender {

    @Autowired
    private MessageReceiver messageReceiver;

    @Override
    public void sendTaskLogMessage(TaskLog taskLog) {
        messageReceiver.receiveTaskLogMessage(taskLog);
    }
}
