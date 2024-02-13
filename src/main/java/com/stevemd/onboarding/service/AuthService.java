package com.stevemd.onboarding.service;


import com.stevemd.onboarding.payload.request.SignUpRequest;
import com.stevemd.onboarding.responses.UniversalResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    UniversalResponse signUpUser(SignUpRequest signUpRequest);
}
