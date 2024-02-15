package com.stevemd.onboarding.controller;

import com.stevemd.onboarding.config.JwtTokenProvider;
import com.stevemd.onboarding.payload.request.LoginRequest;
import com.stevemd.onboarding.payload.request.SignUpRequest;
import com.stevemd.onboarding.payload.response.LoginResponse;
import com.stevemd.onboarding.responses.CustomerInfoResponse;
import com.stevemd.onboarding.responses.UniversalResponse;
import com.stevemd.onboarding.security.UserDetailsImpl;
import com.stevemd.onboarding.security.UserDetailsServiceImpl;
import com.stevemd.onboarding.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/auth")
@Slf4j
public class RegisterController {


    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private AuthService authService;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;





    public RegisterController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping(value = "/register")
    public UniversalResponse signUpUser(@Valid @RequestBody SignUpRequest signUpRequest){
        return authService.signUpUser(signUpRequest) ;
    }

    @PostMapping("/login")
    public LoginResponse loginUserWithCreatedAuthenticationToken(
            @RequestBody LoginRequest loginRequest
    ) {

        // Not need to autowire Authentication since obtaining it direct from Authentication Manager

        log.warn("email {} {}",loginRequest.getName(),loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getName(),loginRequest.getPassword()));

        final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(loginRequest.getName());

        String jwt = jwtTokenProvider.generateToken(userDetails.getUsername());

        return new LoginResponse(jwt);

//        String token = jwtTokenProvider.generateTokenFromUsername(loginRequest.getName());
//        return ResponseEntity.ok(token);

        //SecurityContextHolder.getContext().setAuthentication(authentication);


        // Authenticate user using email/username & password to create a JWT string with a secret
        // String jwt = jwtTokenProvider.generateTokenFromUsername(authentication);

//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        ResponseCookie jwtCookie = jwtTokenProvider.generateJwtCookie(userDetails);
//
//
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toList());
//
//        CustomerInfoResponse customerInfoResponse = new CustomerInfoResponse(userDetails.getEmail(), roles);

//        return ResponseEntity.ok()
//                .header(HttpHeaders.SET_COOKIE,jwtCookie.toString())
//                .body(customerInfoResponse);
    }
}
