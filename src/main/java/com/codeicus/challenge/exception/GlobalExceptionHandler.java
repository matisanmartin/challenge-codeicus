package com.codeicus.challenge.exception;

import com.codeicus.challenge.dto.ErrorResponseDTO;
import com.codeicus.challenge.model.Operation;
import com.codeicus.challenge.model.Result;
import com.codeicus.challenge.model.TaskLog;
import com.codeicus.challenge.queue.sender.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private MessageSender messageSender;

    public ErrorResponseDTO handleBusinessException(BusinessException e) {
        LOGGER.error("BusinessException thrown", e);
        messageSender.sendTaskLogMessage(e.getTaskLog());
        return new ErrorResponseDTO(e.getMessage());
    }

    public ErrorResponseDTO handleServerException(ServerException e) {
        LOGGER.error("ServerException thrown", e);
        messageSender.sendTaskLogMessage(e.getTaskLog());
        return new ErrorResponseDTO(e.getMessage());
    }

    public ErrorResponseDTO handleNotFoundException(NotFoundException e) {
        LOGGER.error("NotFoundException thrown", e);
        return new ErrorResponseDTO(e.getMessage());
    }

    public ErrorResponseDTO handleBadRequestException(BadRequestException e) {
        LOGGER.error("BadRequestException thrown", e);
        messageSender.sendTaskLogMessage(e.getTaskLog());
        return new ErrorResponseDTO(e.getMessage());
    }

    public ErrorResponseDTO handleGenericException(Exception e) {
        LOGGER.error("Exception thrown", e);
        messageSender.sendTaskLogMessage(new TaskLog(Optional.empty(), Operation.UNKNOWN, Result.ERROR, "Generic error"));
        return new ErrorResponseDTO(e.getMessage());
    }
}
