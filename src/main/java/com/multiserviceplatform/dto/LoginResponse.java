package com.multiserviceplatform.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String role;

    public String getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
