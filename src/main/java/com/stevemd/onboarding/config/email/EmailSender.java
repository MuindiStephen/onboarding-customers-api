package com.stevemd.onboarding.config.email;

public interface EmailSender {
    void sendVerificationEmail(String to, String email);
}
