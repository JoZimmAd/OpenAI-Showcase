package de.adesso.openaishowcase.OpenAIApi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiRequest {
    private static final String OPENAI_URL = "https://api.openai.com/v1/chat/completions";

    @Value("${API.key}")
    private String apiKey;
    private final RestTemplate restTemplate = new RestTemplate();

    public String askQuestion(String prompt){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);
        HttpEntity<String> request = new HttpEntity<>(new RequestWrapper().wrap(prompt), headers);
        ResponseEntity<String> response = restTemplate.postForEntity(OPENAI_URL, request, String.class);
        return response.getBody();
    }

    public String getApiKey(){
        return this.apiKey;
    }
}