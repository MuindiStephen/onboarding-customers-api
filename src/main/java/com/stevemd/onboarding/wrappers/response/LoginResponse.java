package com.stevemd.onboarding.wrappers.response;

import lombok.*;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginResponse {
    private String status;
    private String accessToken;
    private String refreshToken;
    private String message;
}
