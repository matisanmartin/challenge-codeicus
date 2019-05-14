package com.codeicus.challenge.exception;

import com.codeicus.challenge.model.TaskLog;

public class BadRequestException extends BaseException {

    public BadRequestException(Exception e, TaskLog taskLog) {
        super(e, taskLog);
    }

    public BadRequestException(String message, TaskLog taskLog) {
        super(message, taskLog);
    }
}
