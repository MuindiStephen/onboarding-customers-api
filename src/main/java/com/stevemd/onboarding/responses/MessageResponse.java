package com.stevemd.onboarding.responses;

import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import lombok.*;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonSerializableSchema
public class MessageResponse {
    private String message;
    private HttpStatus status;
}
