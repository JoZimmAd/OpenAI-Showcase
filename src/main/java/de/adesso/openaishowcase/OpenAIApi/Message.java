package de.adesso.openaishowcase.OpenAIApi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {

    @JsonProperty("role")
    private String role;

    @JsonProperty("content")
    private String answer;

    public String getRole() {return role;}

    public void setRole(String role) {this.role = role;}

    public String getAnswer() {return answer;}

    public void setAnswer(String answer) {this.answer = answer;}
}
