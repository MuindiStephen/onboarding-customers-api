package com.stevemd.onboarding.controller;

import com.stevemd.onboarding.config.JwtTokenProvider;
import com.stevemd.onboarding.payload.request.LoginRequest;
import com.stevemd.onboarding.payload.request.SignUpRequest;
import com.stevemd.onboarding.payload.response.LoginResponse;
import com.stevemd.onboarding.responses.UniversalResponse;
import com.stevemd.onboarding.security.UserDetailsServiceImpl;
import com.stevemd.onboarding.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth/register")
@Slf4j
public class RegisterController {


    @Autowired
    private AuthService authService;


    public RegisterController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public UniversalResponse signUpUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return authService.signUpUser(signUpRequest);
    }

    @GetMapping(path = "/verify-email")
    public UniversalResponse confirmSignUpUserToken(@RequestParam("token") String token) {
        return authService.confirmToken(token);
    }
}
