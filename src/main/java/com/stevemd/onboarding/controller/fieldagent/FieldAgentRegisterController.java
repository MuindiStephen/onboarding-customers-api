package com.stevemd.onboarding.controller.fieldagent;

import com.stevemd.onboarding.responses.UniversalResponse;
import com.stevemd.onboarding.service.fieldagent.FieldAgentAuthService;
import com.stevemd.onboarding.wrappers.request.SignUpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth/fieldagent/register")
@Slf4j
public class FieldAgentRegisterController {

    @Autowired
    private FieldAgentAuthService authService;

    public FieldAgentRegisterController(FieldAgentAuthService authService) {
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
