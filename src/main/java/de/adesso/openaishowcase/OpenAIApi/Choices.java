package de.adesso.openaishowcase.OpenAIApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class Choices {

    @JsonProperty("index")
    private int index;

    @JsonProperty("message")
    private Message message;

    @JsonProperty("finish_reason")
    private String finishReason;


    public int getIndex() {return index;}

    public void setIndex(int index) {this.index = index;}

    public Message getMessage() {return message;}

    public void setMessage(Message message) {this.message = message;}

    public String getFinishReason() {return finishReason;}

    public void setFinishReason(String finishReason) {this.finishReason = finishReason;}



}
