package com.stevemd.onboarding.service.farmer;


import com.stevemd.onboarding.wrappers.request.LoginRequest;
import com.stevemd.onboarding.wrappers.request.SignUpRequest;
import com.stevemd.onboarding.wrappers.response.LoginResponse;
import com.stevemd.onboarding.responses.UniversalResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    UniversalResponse signUpUser(SignUpRequest signUpRequest);

    LoginResponse signinUser(LoginRequest loginRequest);

    UniversalResponse confirmToken(String token);
}
