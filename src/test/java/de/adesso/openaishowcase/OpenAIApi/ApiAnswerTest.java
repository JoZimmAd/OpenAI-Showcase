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

        String prompt = "What is the content of the following E-Mail: Dear Mr. X, we tried to reach you because of your car insurance. Please call back as soon as possible. Kind Regards, Mrs. Y";

        String answerstring = new ApiRequest().askQuestion(prompt);
        ApiAnswer answer = new ObjectMapper().readValue(answerstring, ApiAnswer.class);

        System.out.println(answer.getId());
        System.out.println(answer.getUsage().getPromtTokens());
        System.out.println(answer.getChoices()[0].getMessage().getAnswer());
    }

}
