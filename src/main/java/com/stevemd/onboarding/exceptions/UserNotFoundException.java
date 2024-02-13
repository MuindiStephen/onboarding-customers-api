package com.stevemd.onboarding.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Setter
@Getter
@Builder
public class UserNotFoundException extends UsernameNotFoundException {
    private String message;
    private String status;

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, String status) {
        super(message);
        this.status = status;
    }
}
