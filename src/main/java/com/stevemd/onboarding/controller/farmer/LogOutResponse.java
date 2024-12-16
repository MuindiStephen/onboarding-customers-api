package com.stevemd.onboarding.controller.farmer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LogOutResponse {
    private String status;
    private String message;
}
