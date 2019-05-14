package com.codeicus.challenge.exception;

import com.codeicus.challenge.model.TaskLog;

public class BusinessException extends BaseException {

    public BusinessException(Exception e, TaskLog taskLog) {
        super(e, taskLog);
    }
}
