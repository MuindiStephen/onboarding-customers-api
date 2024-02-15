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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
@Slf4j
public class RegisterController {


    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private AuthService authService;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    public RegisterController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/register")
    public UniversalResponse signUpUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return authService.signUpUser(signUpRequest);
    }

    @PostMapping("/login")
    public LoginResponse loginUserWithCreatedAuthenticationToken(
            @RequestBody LoginRequest loginRequest
    ) {
        // Not need to autowire Authentication since obtaining it direct from Authentication Manager
        try {
            log.warn("email {} {}", loginRequest.getEmail(), loginRequest.getPassword());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(), loginRequest.getPassword()));

            final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(loginRequest.getEmail());

            String jwt = jwtTokenProvider.generateToken(userDetails.getUsername());

            return new LoginResponse(jwt, "You logged in successfully");
        } catch (Exception e) {
            log.error("Error occurred during login: {}", e.getMessage());
            return LoginResponse.builder()
                    .message("Bad credentials: User not found " + HttpStatus.FORBIDDEN)
                    .build();
        }
    }
}
