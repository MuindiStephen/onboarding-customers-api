package com.stevemd.onboarding.controller;

import com.stevemd.onboarding.dto.LoginRequest;
import com.stevemd.onboarding.dto.LoginResponse;
import com.stevemd.onboarding.model.User;
import com.stevemd.onboarding.service.AuthService;
import com.stevemd.onboarding.service.jwt.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.naming.NameNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;


    @PostMapping("/login")
    public LoginResponse loginUserWithCreatedAuthenticationToken(
            @RequestBody LoginRequest loginRequest, HttpServletResponse httpServletResponse
    ) throws BadCredentialsException, DisabledException, NameNotFoundException, IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),loginRequest.getPassword()
            ));

        } catch (BadCredentialsException e){
             throw new BadCredentialsException("Incorrect username or password");
        } catch (DisabledException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_FOUND,"User does not exist. Create Account first");
            return null;
        }
        final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(loginRequest.getEmail());
        final String jwt =null;
        return null;
    }
}

