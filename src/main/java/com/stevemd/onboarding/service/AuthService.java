package com.stevemd.onboarding.service;


import com.stevemd.onboarding.model.User;
import com.stevemd.onboarding.payload.request.SignUpRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    User signUpUser(SignUpRequest signUpRequest);
}
