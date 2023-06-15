package de.adesso.openaishowcase.OpenAIApi;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiAnswer {

    private String id;
    private String object;
    private String created;
    private String model;
    private Usage usage;
    private Choices[] choices;

    public ApiAnswer() {}

    @JsonGetter("object")
    public String getObject() {return object;}

    @JsonSetter
    public void setObject(String object) {this.object = object;}

    @JsonGetter("created")
    public String getCreated() {return created;}

    @JsonSetter
    public void setCreated(String created) {this.created = created;}

    @JsonGetter("id")
    public String getId(){return id;}

    @JsonSetter
    public void setId(){this.id = id; }

    @JsonGetter("model")
    public String getModel(){return model;}

    @JsonSetter
    public void setModel(){this.model = model; }

    @JsonGetter("usage")
    public Usage getUsage(){return usage;}

    @JsonSetter
    public void setUsage(){this.usage = usage; }

    @JsonGetter("choices")
    public Choices[] getChoices() {return choices;}

    @JsonSetter
    public void setChoices(Choices[] choices) {this.choices = choices;}
}
