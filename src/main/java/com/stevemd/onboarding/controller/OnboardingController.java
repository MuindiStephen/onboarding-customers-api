package com.stevemd.onboarding.controller;
import com.stevemd.onboarding.utils.AppUtils;
import org.springframework.web.bind.annotation.*;


@RestController
public class OnboardingController {
    @GetMapping("/api/v2/onboarding")
    public String hello() {
        return "Welcome to Onboarding!!!";
    }
}
