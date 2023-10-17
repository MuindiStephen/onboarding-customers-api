package com.stevemd.onboarding.service;


import com.stevemd.onboarding.dto.SignUpRequest;
import com.stevemd.onboarding.dto.UserDTO;
import com.stevemd.onboarding.model.User;
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


    @Override
    public UserDTO signUpUser(SignUpRequest signUpRequest) {


        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        User signUpUser = userRepository.save(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(signUpUser.getId());
        userDTO.setName(signUpUser.getName());
        userDTO.setEmail(signUpUser.getEmail());
//        userDTO.setPassword(signUpUser.getPassword());
        return userDTO;
    }
}
