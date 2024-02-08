package com.stevemd.onboarding.response;

import lombok.*;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MessageResponse {
    private String message;
    private HttpStatus status;
}
