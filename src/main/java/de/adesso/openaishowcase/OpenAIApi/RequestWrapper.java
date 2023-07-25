package de.adesso.openaishowcase.OpenAIApi;

import de.adesso.openaishowcase.Enums.Category;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class RequestWrapper {

    public String wrap(String prompt) {

        prompt = prompt.replaceAll("\"", "");

        String returnString = "{\"model\": \"gpt-3.5-turbo\"," +
                "\"messages\":[{\"role\": \"user\", \"content\":\"" + prompt + "\"}]," +
                "\"temperature\": 0.7}";

        return returnString;
    }

    public String wrap(String prompt, String prompt2) {

        prompt = prompt.replaceAll("\"", "");

        String returnString = "{\"model\": \"gpt-3.5-turbo\"," +
                "\"messages\":[{\"role\": \"user\", \"content\":\"" + prompt + "\"}," +
                "{\"role\": \"user\", \"content\":\"" + prompt2 + "\"}]," +
                "\"temperature\": 0.7}";

        return returnString;
    }

    public String wrap_categorize(String prompt) {

        List<Category> enumValues = new ArrayList<Category>(EnumSet.allOf(Category.class));
        prompt = prompt.replaceAll("\"", "");

        String returnString = "{\"model\": \"gpt-3.5-turbo\"," +
                "\"messages\":[{\"role\": \"system\", \"content\":\" Die Kategorien um Emails zu kategorisieren sind " + enumValues.toString() + "\"}," +
                "{\"role\": \"user\", \"content\":\"" + prompt + "\"}]," +
                "\"temperature\": 0.2}";

        return returnString;
    }

}
