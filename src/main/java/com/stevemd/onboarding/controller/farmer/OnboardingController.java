package com.stevemd.onboarding.controller.farmer;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth/")
public class OnboardingController {

    @CrossOrigin(origins = "*")
    @GetMapping(value = {"onboarding","testing"})
    public String hello() {
        return "Welcome to Onboarding!!!";
    }
}
