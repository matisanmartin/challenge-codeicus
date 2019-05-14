package com.codeicus.challenge.exception;

import com.codeicus.challenge.model.TaskLog;

public class ServerException extends BaseException {

    public ServerException(Exception e, TaskLog taskLog) {
        super(e, taskLog);
    }
}
