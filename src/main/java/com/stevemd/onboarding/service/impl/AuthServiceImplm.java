package com.stevemd.onboarding.service.impl;


import com.stevemd.onboarding.model.Role;
import com.stevemd.onboarding.model.User;
import com.stevemd.onboarding.payload.request.SignUpRequest;
import com.stevemd.onboarding.repository.RoleRepository;
import com.stevemd.onboarding.response.UniversalResponse;
import com.stevemd.onboarding.response.UserDTO;
import com.stevemd.onboarding.repository.UserRepository;
import com.stevemd.onboarding.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImplm implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UniversalResponse signUpUser(SignUpRequest signUpRequest) {

        if (userRepository.existsByName(signUpRequest.getName())) {

            // TODO - implement json here as return type
            return UniversalResponse.builder()
                    .status("1")
                    .message("Failed. Please input another username")
                    .data(null)
                    .build();
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return UniversalResponse.builder()
                    .message("Failed. Please input another email")
                    .status("1")
                    .data(null)
                    .build();
        }

        // Use builder instead
         User user1 = User.builder()
                 .name(signUpRequest.getName())
                 .email(signUpRequest.getEmail())
                 .password(passwordEncoder.encode(signUpRequest.getPassword()))
                 .build();

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_NAME").get();
        roles.add(userRole);
        user1.setRoles(roles);

        userRepository.save(user1);

        return UniversalResponse.builder()
                .status("0")
                .message(HttpStatus.CREATED+" Success: User registered successfully")
                .data(null)
                .build();
    }
}
