package com.stevemd.onboarding.responses;

import java.util.List;

public class CustomerInfoResponse {
    private String email;
    private List<String> roles;

    public CustomerInfoResponse(String email, List<String> roles) {
        this.email = email;
        this.roles = roles;
    }

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}

