package com.stevemd.onboarding.service.fieldagent;


import com.stevemd.onboarding.config.JwtTokenProvider;
import com.stevemd.onboarding.config.email.EmailSender;
import com.stevemd.onboarding.config.token.ConfirmationToken;
import com.stevemd.onboarding.config.token.ConfirmationTokenService;
import com.stevemd.onboarding.model.UserFieldAgent;
import com.stevemd.onboarding.repository.fieldagent.FieldAgentUserRepository;
import com.stevemd.onboarding.responses.UniversalResponse;
import com.stevemd.onboarding.security.UserDetailsServiceImpl;
import com.stevemd.onboarding.wrappers.request.LoginRequest;
import com.stevemd.onboarding.wrappers.request.SignUpRequest;
import com.stevemd.onboarding.wrappers.response.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FieldAgentAuthServiceImplm implements FieldAgentAuthService {

    @Autowired
    private final FieldAgentUserRepository userRepository;

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
    public FieldAgentAuthServiceImplm(
            FieldAgentUserRepository userRepository,
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
        UserFieldAgent user1 = UserFieldAgent.builder()
                .name(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .build();


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

            if (userRepository.existsByEmail(loginRequest.getEmail())) {

                String accessToken = jwtTokenProvider.generateAccessToken(loginRequest.getEmail());
                String refreshToken = jwtTokenProvider.generateRefreshToken(loginRequest.getPassword());

                return new LoginResponse("00", accessToken, refreshToken, "Field Agent logged in successfully");
            } else {
                return new LoginResponse("1", null, null, "Field agent could not login, details not found in the system");
            }

        } catch (Exception e) {
            log.error("Error occurred during login: {}", e.getMessage());
            return LoginResponse.builder()
                    .status("1")
                    .message("Bad credentials: Field agent not found | " + HttpStatus.FORBIDDEN)
                    .build();
        }

    }

    @Override
    public UniversalResponse confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid token"));

        return UniversalResponse.builder()
                .status("0")
                .message("Email verified successfully")
                .build();
    }
}
