package com.stevemd.onboarding.service;


import com.stevemd.onboarding.model.User;
import com.stevemd.onboarding.payload.request.SignUpRequest;
import com.stevemd.onboarding.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImplm implements AuthService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    /**
     * Create user's account
     * @param signUpRequest
     * @return
     */

    @Override
    public User signUpUser(SignUpRequest signUpRequest) {


        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        User signUpUser = userRepository.save(user);

        user.setId(signUpUser.getId());
        user.setName(signUpUser.getName());
        user.setEmail(signUpUser.getEmail());
//        userDTO.setPassword(signUpUser.getPassword());

// NB: Not to reveal user's password during signup.

        return user;
    }
}
