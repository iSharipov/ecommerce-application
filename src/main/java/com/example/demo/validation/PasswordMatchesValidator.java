package com.example.demo.validation;

import com.example.demo.config.security.JwtRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, JwtRequest> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(JwtRequest jwtRequest, ConstraintValidatorContext context) {
        return jwtRequest.getPassword().equals(jwtRequest.getConfirmPassword());
    }
}