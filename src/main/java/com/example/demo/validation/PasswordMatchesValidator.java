package com.example.demo.validation;

import com.example.demo.model.requests.CreateUserRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, CreateUserRequest> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(CreateUserRequest createUserRequest, ConstraintValidatorContext context) {
        return createUserRequest.getPassword().equals(createUserRequest.getConfirmPassword());
    }
}