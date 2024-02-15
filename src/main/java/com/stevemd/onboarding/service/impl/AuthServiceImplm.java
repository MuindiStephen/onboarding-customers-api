package com.stevemd.onboarding.service.impl;


import com.stevemd.onboarding.config.JwtTokenProvider;
import com.stevemd.onboarding.model.Role;
import com.stevemd.onboarding.model.RoleName;
import com.stevemd.onboarding.model.User;
import com.stevemd.onboarding.payload.request.LoginRequest;
import com.stevemd.onboarding.payload.request.SignUpRequest;
import com.stevemd.onboarding.payload.response.LoginResponse;
import com.stevemd.onboarding.repository.RoleRepository;
import com.stevemd.onboarding.repository.UserRepository;
import com.stevemd.onboarding.responses.UniversalResponse;
import com.stevemd.onboarding.security.UserDetailsServiceImpl;
import com.stevemd.onboarding.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class AuthServiceImplm implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    public AuthServiceImplm(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository =  roleRepository;
    }

    @Override
    public UniversalResponse signUpUser(SignUpRequest signUpRequest) {
        if (userRepository.existsByName(signUpRequest.getName())) {
            return UniversalResponse.builder()
                    .status("1")
                    .message("Failed. Please input another username")
                    .data(null)
                    .build();
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return UniversalResponse.builder()
                    .status("1")
                    .message("Failed. Please input another email")
                    .data(null)
                    .build();
        }


        if (userRepository.existsByEmail(signUpRequest.getEmail()) || userRepository.existsByName(signUpRequest.getName())) {
            return UniversalResponse.builder()
                    .status("1")
                    .message("username and email already taken")
                    .data(null)
                    .build();
        }

        // Use builder instead
        User user1 = User.builder()
                .name(signUpRequest.getName())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .build();


        // Setting the specific role to the user
        Set<RoleName> role = signUpRequest.getRoles(); // retrieves roles from SignUpRequest where getRoles() retrieves a set of RoleNames
        Set<Role> roles = new HashSet<>(); //Initializes an empty HashSet named roles. This HashSet will hold instances of the Role entity,
        if (role==null)
            return UniversalResponse.builder()
                .data(null)
                .message("No user role")
                .status("1")
                .build();

        user1.setRoles(roles);

        userRepository.save(user1);

        log.info("------User Details and Profile:----");
        log.info("Name: {}", signUpRequest.getName());
        log.info("Email: {}", signUpRequest.getEmail());
        log.info("Roles: {}", signUpRequest.getRoles());

        // Access user's id after being created
        Long id = user1.getId();

        return UniversalResponse.builder()
                .status("0")
                .message("Success")
                .data("User id is "+id)
                .build();
    }

    @Override
    public LoginResponse signinUser(LoginRequest loginRequest) {
        try {
            log.warn("email {} {}", loginRequest.getEmail(), loginRequest.getPassword());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(), loginRequest.getPassword()));

            final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(loginRequest.getEmail());

            String accessToken = jwtTokenProvider.generateAccessToken(userDetails.getUsername());
            String refreshToken = jwtTokenProvider.generateRefreshToken(userDetails.getUsername());

            return new LoginResponse(accessToken,refreshToken, "You logged in successfully");

        } catch (Exception e) {
            log.error("Error occurred during login: {}", e.getMessage());
            return LoginResponse.builder()
                    .message("Bad credentials: User not found | " + HttpStatus.FORBIDDEN)
                    .build();
        }
    }
}
