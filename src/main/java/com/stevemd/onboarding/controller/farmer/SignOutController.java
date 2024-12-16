package com.stevemd.onboarding.controller.farmer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
@CrossOrigin(origins = "*")
public class SignOutController {

    /**
     *
     * Handle sign out functionality
     */
    @PostMapping("logout")
    public LogOutResponse signOut () {
       return LogOutResponse.builder()
               .status("0")
               .message("You logged out successfully")
               .build();
    }
}


