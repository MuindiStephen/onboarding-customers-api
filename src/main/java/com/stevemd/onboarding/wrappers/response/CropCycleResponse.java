package com.stevemd.onboarding.wrappers.response;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CropCycleResponse {
    private String status;
    private String message;
}
