package com.stevemd.onboarding.config.token;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }


    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }


    public String generateToken(String name) {
       // To generate a random six-digit code for email verification instead
        Random random = new Random();
        int code = 100000 + random.nextInt(999999); // Generate a random number between 100000 and 999999
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Welcome to Onboarding</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>Hi " + name + ",</h1>\n" +
                "    <p>Thank you for signing up! Please verify your email address to complete the registration process.</p>\n" +
                "    <p>The generated token is: <strong>" + code + "</strong></p>\n" +
                "</body>\n" +
                "</html>\n";
    }

    public void delete(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.delete(confirmationToken);
    }

}
