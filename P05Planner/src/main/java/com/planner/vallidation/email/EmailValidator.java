package com.planner.vallidation.email;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<EmailForm, String> {

    private String message;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9]+@[A-Za-z0-9-.]+$");

    @Override
    public void initialize(EmailForm constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (EMAIL_PATTERN.matcher(value).matches()) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();

        return false;
    }
}
