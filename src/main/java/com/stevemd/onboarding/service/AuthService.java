package com.stevemd.onboarding.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.stevemd.onboarding.payload.request.SignUpRequest;
import com.stevemd.onboarding.response.UniversalResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    UniversalResponse signUpUser(SignUpRequest signUpRequest);
}
