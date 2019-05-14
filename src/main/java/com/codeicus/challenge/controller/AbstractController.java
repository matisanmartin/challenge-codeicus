package com.codeicus.challenge.controller;

import com.codeicus.challenge.exception.BadRequestException;
import com.codeicus.challenge.model.Operation;
import com.codeicus.challenge.model.Result;
import com.codeicus.challenge.model.TaskLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;

import java.util.Optional;

public abstract class AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);
    private static final String REQUEST_IS_NOT_VALID = "Request is not valid";
    private static final String BAD_REQUEST_ERRORS = "Bad request, errors {}";
    private static final String VALIDATION_ERRORS = "Validation errors";

    protected void validateRequest(BindingResult bindingResult, Operation operation) {
        if(bindingResult.hasErrors()) {
            LOGGER.error(BAD_REQUEST_ERRORS, bindingResult.getAllErrors());
            throw new BadRequestException(REQUEST_IS_NOT_VALID, new TaskLog(Optional.empty(), operation, Result.ERROR, VALIDATION_ERRORS));
        }
    }
}
