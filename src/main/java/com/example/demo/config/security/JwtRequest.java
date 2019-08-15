package com.example.demo.config.security;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class JwtRequest {
    @NotEmpty
    @Size(max = 20)
    private String username;
    @NotEmpty
    @Size(min = 5, max = 8)
    private String password;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
