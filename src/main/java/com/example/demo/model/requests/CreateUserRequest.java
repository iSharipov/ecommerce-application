package com.example.demo.model.requests;

import com.example.demo.validation.PasswordMatches;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@PasswordMatches
public class CreateUserRequest {

    @JsonProperty
    @NotEmpty
    @Size(max = 20)
    private String username;
    @JsonProperty
    @NotEmpty
    @Size(min = 5, max = 8)
    private String password;
    @NotEmpty
    @Size(min = 5, max = 8)
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
