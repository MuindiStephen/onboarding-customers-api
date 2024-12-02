package com.stevemd.onboarding.controller;

import com.stevemd.onboarding.wrappers.request.SignUpRequest;
import com.stevemd.onboarding.responses.UniversalResponse;
import com.stevemd.onboarding.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @CrossOrigin(origins = "*")
    @PostMapping
    public UniversalResponse signUpUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return authService.signUpUser(signUpRequest);
    }

    @CrossOrigin(origins = "*") // allow access from angular frontend
    @GetMapping(path = "/verify-email")
    public UniversalResponse confirmSignUpUserToken(@RequestParam("token") String token) {
        return authService.confirmToken(token);
    }
}
