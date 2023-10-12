package com.stevemd.onboarding.service;


import com.stevemd.onboarding.dto.LoginRequest;
import com.stevemd.onboarding.dto.SignUpRequest;
import com.stevemd.onboarding.dto.UserDTO;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    UserDTO signUpUser(SignUpRequest signUpRequest);

    Authentication signInUser(LoginRequest loginRequest);
}
