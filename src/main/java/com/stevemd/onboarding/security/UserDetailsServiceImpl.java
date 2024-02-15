package com.stevemd.onboarding.security;

import com.stevemd.onboarding.exceptions.UserNotFoundException;
import com.stevemd.onboarding.model.User;
import com.stevemd.onboarding.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        log.info("Loading user by username: {}",name);
        //log.info();

        User user = userRepository.findByName(name)
                .orElseThrow(() -> UserNotFoundException.builder()
                        .message("User not found with username: {}"+name)
                        .status("HTTP :404 not found")
                        .build());

        log.info("User found:{}",name);

       return UserDetailsImpl.build(user);
    }
}
