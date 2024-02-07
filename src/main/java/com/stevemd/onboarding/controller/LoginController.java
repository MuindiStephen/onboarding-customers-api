package com.stevemd.onboarding.controller;

import com.stevemd.onboarding.payload.request.LoginRequest;
import com.stevemd.onboarding.payload.response.JwtResponse;
import com.stevemd.onboarding.payload.response.LoginResponse;
import com.stevemd.onboarding.security.jwt.JwtUtils;
import com.stevemd.onboarding.security.services.UserDetailsImpl;
import com.stevemd.onboarding.security.services.UserDetailsServiceImpl;
import com.stevemd.onboarding.service.AuthService;
import com.stevemd.onboarding.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.NameNotFoundException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "https://localhost:8088/")  // ("*")
@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    private  Authentication authentication;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtils jwtUtils;


    @PostMapping(AppUtils.BASE_URL+"/login")
    public ResponseEntity<?> loginUserWithCreatedAuthenticationToken( @Valid
            @RequestBody LoginRequest loginRequest
    ) {

        // Not need to autowire Authentication since obtaining it direct from Authentication Manager
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);


        // Authenticate user using email/username & password to create a JWT string with a secret
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
}

