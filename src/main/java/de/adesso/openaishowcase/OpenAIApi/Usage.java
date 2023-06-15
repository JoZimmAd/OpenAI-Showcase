package de.adesso.openaishowcase.OpenAIApi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Usage {

    @JsonProperty("prompt_tokens")
    private int promtTokens;

    @JsonProperty("completion_tokens")
    private int completiontokens;

    @JsonProperty("total_tokens")
    private int totalTokens;

    public int getPromtTokens() {
        return promtTokens;
    }

    public void setPromtTokens(int promtTokens) {
        this.promtTokens = promtTokens;
    }

    public int getCompletiontokens() {
        return completiontokens;
    }

    public void setCompletiontokens(int completiontokens) {
        this.completiontokens = completiontokens;
    }

    public int getTotalTokens() {
        return totalTokens;
    }

    public void setTotalTokens(int totalTokens) {
        this.totalTokens = totalTokens;
    }
}
