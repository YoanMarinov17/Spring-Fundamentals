package com.planner.vallidation.login;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = UserLoginValidator.class)
public @interface UserLoginForm {

    String message() default "Incorrect username or password!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}