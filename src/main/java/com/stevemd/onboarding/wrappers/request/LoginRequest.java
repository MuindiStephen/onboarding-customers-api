package com.stevemd.onboarding.wrappers.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/**
 * @Data tells lombok library to automatically generate getter, setters, toString etc. methods for @LoginRequest class
 */
@Data
@Getter
@Setter
public class LoginRequest {


    private String email;


    private String password;
}
