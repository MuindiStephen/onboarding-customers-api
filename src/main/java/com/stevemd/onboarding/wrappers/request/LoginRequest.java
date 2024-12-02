package com.stevemd.onboarding.wrappers.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


/**
 * @Data tells lombok library to automatically generate getter, setters, toString etc. methods for @LoginRequest class
 */
@Data
@Getter
@Setter
public class LoginRequest {

    @NotEmpty(message = "Email is invalid")
    @Email
    private String email;

    @Size(min = 6, max = 100)
    @NotEmpty(message = "Password is invalid")
    private String password;
}
