package com.stevemd.onboarding.controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth/")
public class OnboardingController {
    @GetMapping(value = {"onboarding","testing"})
    public String hello() {
        return "Welcome to Onboarding!!!";
    }
}
