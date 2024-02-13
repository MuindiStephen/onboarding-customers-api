package com.stevemd.onboarding.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class UniversalResponse {
    private String status;
    private String message;
    private Object data;
}
