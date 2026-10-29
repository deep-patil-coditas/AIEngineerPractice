package org.example.emailaiengineer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientConfig {

    @Bean
    public RestClient llmRestClient(@Value("${llm.base-url}") String baseUrl,
                                    @Value("${llm.api-key}") String apiKey,
                                    @Value("${llm.api-version}") String apiVersion){
        return RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("x-api-key",apiKey)
                .defaultHeader("anthropic-version",apiVersion)
                .defaultHeader("content-type", MediaType.APPLICATION_JSON_VALUE)
                .build();

    }
}
