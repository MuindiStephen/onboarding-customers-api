package com.stevemd.onboarding.payload.request;


import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class SignUpRequest {

    private String name;

    @Email
    private String email;

    private String password;
}
