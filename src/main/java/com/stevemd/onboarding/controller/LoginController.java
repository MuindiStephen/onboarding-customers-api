package com.stevemd.onboarding.controller;

import com.stevemd.onboarding.config.JwtTokenProvider;
import com.stevemd.onboarding.payload.request.LoginRequest;
import com.stevemd.onboarding.payload.response.LoginResponse;
import com.stevemd.onboarding.responses.CustomerInfoResponse;
import com.stevemd.onboarding.security.UserDetailsImpl;
import com.stevemd.onboarding.security.UserDetailsServiceImpl;
import com.stevemd.onboarding.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class LoginController {



//
//
//    @PostMapping("/login")
//    public ResponseEntity<?> loginUserWithCreatedAuthenticationToken( @Valid
//            @RequestBody LoginRequest loginRequest
//    ) {
//
//        // Not need to autowire Authentication since obtaining it direct from Authentication Manager
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                loginRequest.getEmail(),loginRequest.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//
//        // Authenticate user using email/username & password to create a JWT string with a secret
//       // String jwt = jwtTokenProvider.generateTokenFromUsername(authentication);
//
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        ResponseCookie jwtCookie = jwtTokenProvider.generateJwtCookie(userDetails);
//
//
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toList());
//
//        CustomerInfoResponse customerInfoResponse = new CustomerInfoResponse(userDetails.getEmail(), roles);
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.SET_COOKIE,jwtCookie.toString())
//                .body(customerInfoResponse);
//    }
}

