package com.codeicus.challenge.validator;

import com.codeicus.challenge.validator.annotation.Match;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class MatchValidator implements ConstraintValidator<Match, String> {

    private String regex;
    private boolean caseSensitive;

    @Override
    public void initialize(Match match) {
        this.regex = match.regex();
        this.caseSensitive = match.caseSensitive();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return true;
        } else {
            Pattern pattern;
            if (!this.caseSensitive) {
                pattern = Pattern.compile(this.regex, 2);
            } else {
                pattern = Pattern.compile(this.regex);
            }

            return pattern.matcher(s).matches();
        }
    }
}
