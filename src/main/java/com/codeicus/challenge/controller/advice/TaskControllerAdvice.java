package com.codeicus.challenge.controller.advice;

import com.codeicus.challenge.controller.TaskController;
import com.codeicus.challenge.dto.ErrorResponseDTO;
import com.codeicus.challenge.exception.BadRequestException;
import com.codeicus.challenge.exception.BusinessException;
import com.codeicus.challenge.exception.NotFoundException;
import com.codeicus.challenge.exception.ServerException;
import com.codeicus.challenge.queue.RabbitMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = TaskController.class)
public class TaskControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskControllerAdvice.class);
    @Autowired
    private RabbitMessageSender rabbitMessageSender;

    @ExceptionHandler(value = BusinessException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponseDTO handleBusinessException(BusinessException e) {
        LOGGER.error("BusinessException thrown", e);
        rabbitMessageSender.sendTaskLogMessage(e.getTaskLog());
        return new ErrorResponseDTO(e.getMessage());
    }

    @ExceptionHandler(value = ServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDTO handlerServerException(ServerException e) {
        LOGGER.error("ServerException thrown", e);
        rabbitMessageSender.sendTaskLogMessage(e.getTaskLog());
        return new ErrorResponseDTO(e.getMessage());
    }

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handlerServerException(NotFoundException e) {
        LOGGER.error("NotFoundException thrown", e);
        return new ErrorResponseDTO(e.getMessage());
    }

    @ExceptionHandler(value = BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handlerServerException(BadRequestException e) {
        LOGGER.error("BadRequestException thrown", e);
        rabbitMessageSender.sendTaskLogMessage(e.getTaskLog());
        return new ErrorResponseDTO(e.getMessage());
    }

}
