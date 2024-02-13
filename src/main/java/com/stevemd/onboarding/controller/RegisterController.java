package com.stevemd.onboarding.controller;

import com.stevemd.onboarding.payload.request.SignUpRequest;
import com.stevemd.onboarding.responses.UniversalResponse;
import com.stevemd.onboarding.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;



@RestController
public class RegisterController {

    @Autowired
    private AuthService authService;

    public RegisterController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping(value = "/register")
    public UniversalResponse signUpUser(@Valid @RequestBody SignUpRequest signUpRequest){
        return authService.signUpUser(signUpRequest) ;
    }
}
