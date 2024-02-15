package com.stevemd.onboarding.service.impl;


import com.stevemd.onboarding.model.Role;
import com.stevemd.onboarding.model.RoleName;
import com.stevemd.onboarding.model.User;
import com.stevemd.onboarding.payload.request.LoginRequest;
import com.stevemd.onboarding.payload.request.SignUpRequest;
import com.stevemd.onboarding.repository.RoleRepository;
import com.stevemd.onboarding.repository.UserRepository;
import com.stevemd.onboarding.responses.UniversalResponse;
import com.stevemd.onboarding.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthServiceImplm implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImplm.class);

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

        logger.info("------User Details and Profile:----");
        logger.info("Name: {}", signUpRequest.getName());
        logger.info("Email: {}", signUpRequest.getEmail());
        logger.info("Roles: {}", signUpRequest.getRoles());

        // Access user's id after being created
        Long id = user1.getId();

        return UniversalResponse.builder()
                .status("0")
                .message("Success")
                .data("User id is "+id)
                .build();
    }

    @Override
    public UniversalResponse signinUser(LoginRequest loginRequest) {
        // TODO login action here
        return null;
    }
}
