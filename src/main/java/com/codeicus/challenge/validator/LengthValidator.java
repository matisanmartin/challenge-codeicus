package com.codeicus.challenge.validator;

import com.codeicus.challenge.validator.annotation.Length;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class LengthValidator implements ConstraintValidator<Length, String> {

    private long min;
    private long max;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || (value.length() >= min && value.length() <= max);
    }

    @Override
    public void initialize(Length constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }
}
