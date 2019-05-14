package com.codeicus.challenge.controller.rest.advice;

import com.codeicus.challenge.controller.rest.TaskController;
import com.codeicus.challenge.dto.ErrorResponseDTO;
import com.codeicus.challenge.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = TaskController.class)
public class TaskControllerAdvice {

    @Autowired
    private GlobalExceptionHandler globalExceptionHandler;

    @ExceptionHandler(value = BusinessException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponseDTO handleBusinessException(BusinessException e) {
        return globalExceptionHandler.handleBusinessException(e);
    }

    @ExceptionHandler(value = ServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDTO handleServerException(ServerException e) {
        return globalExceptionHandler.handleServerException(e);
    }

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handleNotFoundException(NotFoundException e) {
        return globalExceptionHandler.handleNotFoundException(e);
    }

    @ExceptionHandler(value = BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleBadRequestException(BadRequestException e) {
        return globalExceptionHandler.handleBadRequestException(e);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDTO handleGenericException(Exception e) {
        return globalExceptionHandler.handleGenericException(e);
    }

}
