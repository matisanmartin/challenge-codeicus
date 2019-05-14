package com.codeicus.challenge.validator.annotation;

import com.codeicus.challenge.validator.MatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {MatchValidator.class}
)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Match {

    String message() default "String doesn't match the expression";

    String regex();

    boolean caseSensitive() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
