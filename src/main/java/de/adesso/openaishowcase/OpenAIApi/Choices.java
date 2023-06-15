package de.adesso.openaishowcase.OpenAIApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class Choices {

    @JsonProperty("index")
    private int index;

    @JsonProperty("message")
    private message message;

    @JsonProperty("finish_reason")
    private String finishReason;


    public int getIndex() {return index;}

    public void setIndex(int index) {this.index = index;}

    public message getMessage() {return message;}

    public void setMessage(de.adesso.openaishowcase.OpenAIApi.message message) {this.message = message;}

    public String getFinishReason() {return finishReason;}

    public void setFinishReason(String finishReason) {this.finishReason = finishReason;}



}
