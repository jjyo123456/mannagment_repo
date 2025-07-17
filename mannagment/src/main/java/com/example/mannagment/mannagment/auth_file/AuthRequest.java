package com.example.mannagment.mannagment.auth_file;

public class AuthRequest {
    private String username;
    private String password;

    public AuthRequest() {
        // No-arg constructor required for Spring
    }

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
}
