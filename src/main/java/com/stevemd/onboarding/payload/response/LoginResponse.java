package com.stevemd.onboarding.payload.response;

import lombok.*;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginResponse {
    private String jwtToken;

    private String message;
}
