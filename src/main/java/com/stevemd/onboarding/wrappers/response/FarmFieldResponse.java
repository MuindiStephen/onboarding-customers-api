package com.stevemd.onboarding.wrappers.response;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FarmFieldResponse {
    private String status;
    private String message;
}
