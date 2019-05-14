package com.codeicus.challenge.queue.receiver;

import com.codeicus.challenge.model.TaskLog;

public interface MessageReceiver {

    void receiveTaskLogMessage(TaskLog taskLog);
}
