package com.stevemd.onboarding.controller.fieldagent;


import com.stevemd.onboarding.service.fieldagent.FieldAgentAuthService;
import com.stevemd.onboarding.wrappers.request.LoginRequest;
import com.stevemd.onboarding.wrappers.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/")
public class FieldAgentLoginController {

    @Autowired
    private FieldAgentAuthService authService;

    @CrossOrigin(origins = "*")
    @PostMapping("fieldagent/login")
    public LoginResponse loginUserWithCreatedAuthenticationToken(
            @RequestBody LoginRequest loginRequest
    ) {
        // Not need to autowire Authentication since obtaining it direct from Authentication Manager
        return authService.signinUser(loginRequest);
    }
}

