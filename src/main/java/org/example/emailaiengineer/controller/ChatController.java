package org.example.emailaiengineer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.emailaiengineer.dtos.ApiResponse;
import org.example.emailaiengineer.dtos.ChatRequest;
import org.example.emailaiengineer.service.ChatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/chat")
    public ResponseEntity<ApiResponse<String>> chat(@Valid @RequestBody ChatRequest request){
        String response = chatService.chat(request.getMessage());
        return ResponseEntity.ok(new ApiResponse<>(true, "LLM Response" ,response, LocalDateTime.now()));
    }


}
