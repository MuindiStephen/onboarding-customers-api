package com.stevemd.onboarding.response;

import lombok.Builder;

@Builder
public class UniversalResponse {
    private String status;
    private String message;
    private Object data;
}
