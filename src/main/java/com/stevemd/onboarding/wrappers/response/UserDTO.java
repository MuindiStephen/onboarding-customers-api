package com.stevemd.onboarding.wrappers.response;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
}
