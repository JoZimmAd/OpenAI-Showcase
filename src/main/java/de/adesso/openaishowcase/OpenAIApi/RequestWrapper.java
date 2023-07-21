package de.adesso.openaishowcase.OpenAIApi;

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

}
