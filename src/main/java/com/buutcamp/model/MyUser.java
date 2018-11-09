package com.buutcamp.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MyUser {
    @NotNull(message="is required")
    @Size(min=1, max=30, message = "is required")
    private String username;

    @NotNull(message="is required")
    @Size(min=1, message = "is required")
    private String password;

    private String role;

    public MyUser() {

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
