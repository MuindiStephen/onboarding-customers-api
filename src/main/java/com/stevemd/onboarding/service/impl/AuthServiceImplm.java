package com.stevemd.onboarding.service.impl;


import com.stevemd.onboarding.model.User;
import com.stevemd.onboarding.payload.request.SignUpRequest;
import com.stevemd.onboarding.response.UniversalResponse;
import com.stevemd.onboarding.response.UserDTO;
import com.stevemd.onboarding.repository.UserRepository;
import com.stevemd.onboarding.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImplm implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UniversalResponse signUpUser(SignUpRequest signUpRequest) {

        if (userRepository.existsByName(signUpRequest.getName())) {
            //return new UserDTO();
            // TODO - implement json here as return type
            return UniversalResponse.builder()
                    .status("1")
                    .message("Failed. Please input another username")
                    .build();

        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return UniversalResponse.builder()
                    .message("Failed. Please input another email")
                    .status("1")
                    .data(null)
                    .build();
        }
        /* **
         * User user = new User();
            user.setName(signUpRequest.getName());
            user.setEmail(signUpRequest.getEmail());
            user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
             userRepository.save(user);
         */

        // Use builder instead
         User user1 = User.builder()
                 .name(signUpRequest.getName())
                 .email(signUpRequest.getEmail())
                 .password(passwordEncoder.encode(signUpRequest.getPassword()))
                 .build();
         userRepository.save(user1);


        return UniversalResponse.builder()
                .status("0")
                .message("Success: User registered successfully")
                .data(null)
                .build();
    }
}
