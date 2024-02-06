package com.stevemd.onboarding.payload.request;

import lombok.Data;

import javax.validation.constraints.Email;


/**
 * @Data tells lombok library to automatically generate getter, setters, toString etc. methods for @LoginRequest class
 */
@Data
public class LoginRequest {

    @Email
    private String email;

    private String password;
}
