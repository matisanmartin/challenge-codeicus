package com.codeicus.challenge.exception.supplier;

import com.codeicus.challenge.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public final class ExceptionSupplier {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionSupplier.class);

    public static Supplier<NotFoundException> notFoundExceptionSupplier() {
        return () -> {
            LOGGER.error("Task not found");
            return new NotFoundException("Entity not found");
        };
    }
}
