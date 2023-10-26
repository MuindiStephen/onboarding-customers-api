package com.stevemd.onboarding.payload;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;
}
