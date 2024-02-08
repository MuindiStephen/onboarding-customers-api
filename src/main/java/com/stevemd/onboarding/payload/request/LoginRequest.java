package com.stevemd.onboarding.payload.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


/**
 * @Data tells lombok library to automatically generate getter, setters, toString etc. methods for @LoginRequest class
 */
@Data
public class LoginRequest {

    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    @NotEmpty(message = "Email should not be empty")
    private String password;
}
