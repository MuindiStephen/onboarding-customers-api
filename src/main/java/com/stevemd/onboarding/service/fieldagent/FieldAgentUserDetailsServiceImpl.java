package com.stevemd.onboarding.service.fieldagent;

import com.stevemd.onboarding.exceptions.UserNotFoundException;
import com.stevemd.onboarding.model.User;
import com.stevemd.onboarding.repository.fieldagent.FieldAgentUserRepository;
import com.stevemd.onboarding.responses.UniversalResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class FieldAgentUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private FieldAgentUserRepository userRepository;

    public FieldAgentUserDetailsServiceImpl(FieldAgentUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        log.info("Loading user by username: {}.....",email);
        //log.info();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> UserNotFoundException.builder()
                        .message("User not found with username: {}"+email)
                        .status("HTTP :404 not found")
                        .build());

       if (user==null){
           handleUserNotFound(email);
       }

        // User lookup in the database
        log.info("User found:{}",email);

       return FieldAgentUserDetailsImpl.build(user);
    }

    public UniversalResponse handleUserNotFound(String email) {
        return UniversalResponse.builder()
                .status("404")
                .message("User not found with username: " + email)
                .build();
    }
}
