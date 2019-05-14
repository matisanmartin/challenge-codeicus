package com.codeicus.challenge.validator.annotation;

import com.codeicus.challenge.validator.LengthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {LengthValidator.class}
)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Length {

    String message() default "Length is invalid";

    long min();

    long max();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
