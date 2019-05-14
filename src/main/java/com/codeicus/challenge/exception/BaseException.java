package com.codeicus.challenge.exception;

import com.codeicus.challenge.model.TaskLog;

public class BaseException extends RuntimeException {

    protected final TaskLog taskLog;

    public BaseException(Exception e, TaskLog taskLog) {
        super(e);
        this.taskLog = taskLog;
    }

    public BaseException(String message, TaskLog taskLog) {
        super(message);
        this.taskLog = taskLog;
    }

    public TaskLog getTaskLog() {
        return taskLog;
    }

}
