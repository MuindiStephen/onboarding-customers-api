package com.stevemd.onboarding.service.fieldagent;


import com.stevemd.onboarding.config.JwtTokenProvider;
import com.stevemd.onboarding.config.email.EmailSender;
import com.stevemd.onboarding.config.token.ConfirmationToken;
import com.stevemd.onboarding.config.token.ConfirmationTokenService;
import com.stevemd.onboarding.model.User;
import com.stevemd.onboarding.repository.fieldagent.UserRepository;
import com.stevemd.onboarding.responses.UniversalResponse;
import com.stevemd.onboarding.security.UserDetailsServiceImpl;
import com.stevemd.onboarding.wrappers.request.LoginRequest;
import com.stevemd.onboarding.wrappers.request.SignUpRequest;
import com.stevemd.onboarding.wrappers.response.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class AuthServiceImplm implements AuthService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private final ConfirmationTokenService confirmationTokenService;

    @Autowired
    private EmailSender emailSender;


    // Instantiation
    public AuthServiceImplm(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            ConfirmationTokenService confirmationTokenService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.confirmationTokenService = confirmationTokenService;
    }

    @Override
    public UniversalResponse signUpUser(SignUpRequest signUpRequest) {
        if (userRepository.existsByName(signUpRequest.getUsername())) {
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


        if (userRepository.existsByEmail(signUpRequest.getEmail()) || userRepository.existsByName(signUpRequest.getUsername())) {
            return UniversalResponse.builder()
                    .status("1")
                    .message("username and email already taken. Already in use by another account.")
                    .data(null)
                    .build();
        }

        // Use builder instead
        User user1 = User.builder()
                .name(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .build();


//        // Setting the specific role to the user
//        Set<RoleName> role = signUpRequest.getRoles(); // retrieves roles from SignUpRequest where getRoles() retrieves a set of RoleNames
//        Set<Role> roles = new HashSet<>(); //Initializes an empty HashSet named roles. This HashSet will hold instances of the Role entity,
//        if (role == null)
//            return UniversalResponse.builder()
//                    .data(null)
//                    .message("No user role")
//                    .status("1")
//                    .build();
//
//        user1.setRoles(roles);


        userRepository.save(user1);

        log.info("------User Details and Profile:----");
        log.info("Name: {}", signUpRequest.getUsername());
        log.info("Email: {}", signUpRequest.getEmail());
       // log.info("Roles: {}", signUpRequest.getRoles());
//
        return UniversalResponse.builder()
                .status("00")
                .message("Registered successfully. \nLogin to the account.")
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

            return new LoginResponse("00",accessToken, refreshToken, "You logged in successfully");

        } catch (Exception e) {
            log.error("Error occurred during login: {}", e.getMessage());
            return LoginResponse.builder()
                    .status("1")
                    .message("Bad credentials: User not found | " + HttpStatus.FORBIDDEN)
                    .build();
        }
    }

    @Override
    public UniversalResponse confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid token"));

        // Check if the token has expired
        if (confirmationToken.getExpiresAt().isAfter(LocalDateTime.now().plusMinutes(15))) {
            // Token has expired, return error response
            User user = confirmationToken.getUser1();
            log.info("---Email verification failed for {} {}" + user.getEmail(), user.getName());

            return UniversalResponse.builder()
                    .status("1")
                    .message("Token has expired")
                    .build();
        }

        if (confirmationToken.getConfirmedAt() != null) {
            return UniversalResponse.builder()
                    .status("1")
                    .message("Email is already verified")
                    .build();
        }
        // Get the associated user
        User user = confirmationToken.getUser1();

        // Mark the user as verified;// Assuming you have an 'enabled' field in your User entity
        // user.setEnabled(true);

        // Save the updated user
        userRepository.save(user);

        // Delete the confirmation token
        confirmationTokenService.delete(confirmationToken);

        log.info("Email verification successful for {} {}" + user.getEmail(), user.getName());
        // Return success response
        return UniversalResponse.builder()
                .status("0")
                .message("Email verified successfully")
                .build();
    }
}
