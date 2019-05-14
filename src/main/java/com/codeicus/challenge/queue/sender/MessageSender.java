package com.codeicus.challenge.queue.sender;

import com.codeicus.challenge.model.TaskLog;

public interface MessageSender {

    void sendTaskLogMessage(TaskLog taskLog);
}
