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


    private String name;


    private String password;
}
