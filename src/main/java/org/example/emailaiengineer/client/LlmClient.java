package org.example.emailaiengineer.client;

import jakarta.validation.Valid;
import org.example.emailaiengineer.exception.LlmException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ArrayNode;
import tools.jackson.databind.node.ObjectNode;


@Component
public class LlmClient {

    private final RestClient llmRestClient;
    private final ObjectMapper objectMapper;
    private final String apiKey;
    private final String model;
    private final int maxToken;

    public LlmClient(RestClient llmRestClient, ObjectMapper objectMapper,
                     @Value("${llm.api-key}") String apiKey,
                     @Value("${llm.model}")String model,
                     @Value("${llm.max-tokens}") int maxToken) {
        this.llmRestClient = llmRestClient;
        this.objectMapper = objectMapper;
        this.apiKey = apiKey;
        this.model = model;
        this.maxToken = maxToken;
    }

    public JsonNode sendMessages(ArrayNode messages) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new LlmException("API_KEY environment variable is not set");
        }

        ObjectNode body = objectMapper.createObjectNode();
        body.put("model", model);
        body.put("max_tokens", maxToken);
        body.set("messages", messages);

        JsonNode response = llmRestClient.post()
                .uri("/v1/messages")
                .body(body)
                .retrieve()
                .body(JsonNode.class);

        if (response == null) {
            throw new LlmException("LLM API returned an empty response");
        }
        return response;
    }

    public String extractText(JsonNode response) {
        StringBuilder text = new StringBuilder();
        for (JsonNode block : response.path("content")) {
            if ("text".equals(block.path("type").asString())) {
                text.append(block.path("text").asString());
            }
        }
        return text.toString();
    }

}
