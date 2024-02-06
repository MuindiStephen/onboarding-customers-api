package com.stevemd.onboarding.service;


import com.stevemd.onboarding.payload.request.SignUpRequest;
import com.stevemd.onboarding.payload.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    UserDTO signUpUser(SignUpRequest signUpRequest);
}
