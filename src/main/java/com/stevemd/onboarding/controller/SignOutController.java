package com.stevemd.onboarding.controller;

import com.stevemd.onboarding.payload.response.MessageResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignOutController {

//    @PostMapping
//    public ResponseEntity<?> signOut () {
////       ResponseCookie cookie = new ResponseCookie();
////       return  ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
////                .body(new MessageResponse("You have been signed out successfully"));
//    }
}
