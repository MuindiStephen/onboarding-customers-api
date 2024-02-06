package com.stevemd.onboarding.payload;

import lombok.Data;


/**
 * @Data tells lombok library to automatically generate getter, setters, toString etc. methods for @LoginRequest class
 */
@Data
public class LoginRequest {
    private String email;
    private String password;
}
