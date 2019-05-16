package com.codeicus.challenge.controller.jsf;

import com.codeicus.challenge.dto.ErrorResponseDTO;
import com.codeicus.challenge.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.function.Consumer;

public class JsfController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsfTaskController.class);
    protected static final String TASK_XHTML = "/task_management.xhtml";
    protected static final String TASKS_XHTML = "/tasks.xhtml";
    protected static final String ERROR_XHTML = "/error.xhtml";
    protected static final String CREATE_TASK_XHTML = "/task_creation.xhtml";
    protected static final String SUCCESS_XHTML = "/success.xhtml";

    protected ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();

    @Autowired
    private GlobalExceptionHandler globalExceptionHandler;

    protected void accept(Consumer<Long> consumer, Long id, String message) {
        LOGGER.info(message);
        try {
            consumer.accept(id);
        } catch(BusinessException e) {
            errorResponseDTO = globalExceptionHandler.handleBusinessException(e);
            redirect(ERROR_XHTML);
        } catch(ServerException e) {
            errorResponseDTO = globalExceptionHandler.handleServerException(e);
            redirect(ERROR_XHTML);
        } catch(NotFoundException e) {
            errorResponseDTO = globalExceptionHandler.handleNotFoundException(e);
            redirect(ERROR_XHTML);
        } catch(BadRequestException e) {
            errorResponseDTO = globalExceptionHandler.handleBadRequestException(e);
            redirect(ERROR_XHTML);
        } catch(Exception e) {
            errorResponseDTO = globalExceptionHandler.handleGenericException(e);
            redirect(ERROR_XHTML);
        }
    }

    protected void redirect(String s) {
        try {
            LOGGER.info("Redirecting to {}", s);
            FacesContext.getCurrentInstance().getExternalContext().redirect(s);
        } catch(IOException e) {
            LOGGER.error("IOException when redirecting", e);
            //TODO ver si hay alguna forma de hacer un redirect en caso de excepcion sin que tire IOException
        }
    }

    public ErrorResponseDTO getErrorResponseDTO() {
        return errorResponseDTO;
    }

    public void setErrorResponseDTO(ErrorResponseDTO errorResponseDTO) {
        this.errorResponseDTO = errorResponseDTO;
    }
}
