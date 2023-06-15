package de.adesso.openaishowcase.OpenAIApi;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ApiAnswerTest {




    public ApiAnswerTest() {

    }

    @Test
    public void should_return_id_attribute_of_JSONString() throws JSONException, ParseException, JsonProcessingException {

        String prompt = "Categorize the words in the following sentence: At Sunday 11am, the quirky fox jumps over the lazy dog.";

        String answerstring = new ApiRequest().askQuestion(prompt);
        ApiAnswer answer = new ObjectMapper().readValue(answerstring, ApiAnswer.class);

        System.out.println(answer.getId());
        System.out.println(answer.getUsage().getPromtTokens());
        System.out.println(answer.getChoices()[0].getMessage().getAnswer());
    }

}
