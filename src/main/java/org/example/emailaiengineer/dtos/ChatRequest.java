package org.example.emailaiengineer.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChatRequest {
    @NotBlank(message = "Can't be null")
    private String message;
}
