package com.stevemd.onboarding.service;


import com.stevemd.onboarding.dto.SignUpRequest;
import com.stevemd.onboarding.dto.UserDTO;
import com.stevemd.onboarding.model.User;
import com.stevemd.onboarding.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImplm implements AuthService{

    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDTO signUpUser(SignUpRequest signUpRequest) {

        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setName(signUpRequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        User signUpUser = userRepository.save(user);
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(signUpUser.getEmail());
        userDTO.setName(signUpUser.getName());
        userDTO.setPassword(signUpUser.getPassword());
        return userDTO;
    }
}
