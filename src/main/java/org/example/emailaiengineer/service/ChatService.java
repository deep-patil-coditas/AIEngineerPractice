package org.example.emailaiengineer.service;

import lombok.RequiredArgsConstructor;
import org.example.emailaiengineer.client.LlmClient;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ArrayNode;
import tools.jackson.databind.node.ObjectNode;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final LlmClient llmClient;
    private final ObjectMapper objectMapper;

    public String chat(String message) {
        ArrayNode messages = objectMapper.createArrayNode();
        ObjectNode userTurn = messages.addObject();
        userTurn.put("role", "user");
        userTurn.put("content", message);
        JsonNode response = llmClient.sendMessages(messages);
        return llmClient.extractText(response);
    }
}
