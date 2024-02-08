package com.stevemd.onboarding.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class JwtResponse {
    private String token;
    @Setter
    @Getter
    private String type;
    @Setter
    @Getter
    private Long id;
    @Getter
    private String username;
    @Getter
    private String email;
    private List<String> role;

    public JwtResponse(String accesstoken, Long id, String username, String email, List<String> role) {
        this.token = accesstoken;
        this.type = type;
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String token) {
        this.token = token;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }
}
