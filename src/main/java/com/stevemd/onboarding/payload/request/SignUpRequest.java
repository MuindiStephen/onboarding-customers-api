package com.stevemd.onboarding.payload.request;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class SignUpRequest {

    @NotBlank
    private String name;

    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    @NotEmpty(message = "Email should not be empty")
    private String password;

    //private Set<String> role; // need to know the role of a user before sign up ie. (admin, normal user, moderator)
}
