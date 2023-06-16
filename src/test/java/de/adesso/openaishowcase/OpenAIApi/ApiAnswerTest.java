package de.adesso.openaishowcase.OpenAIApi;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ApiAnswerTest {


    private long startTime;

    public ApiAnswerTest() {

    }

    @Test
    public void should_return_id_attribute_of_JSONString() throws JSONException, ParseException, JsonProcessingException {

        String prompt_en = "Categorize the words in the following sentence: At Sunday 11am, the quirky fox jumps over the lazy dog.";
        String prompt_de = "Kategorisiere die Worte in folgendem Satz: AM Sonntag um 11 Uhr springt der quirrlige Fuchs über den faulen Hund.";

        String answerstring = new ApiRequest().askQuestion(prompt_de);
        ApiAnswer answer = new ObjectMapper().readValue(answerstring, ApiAnswer.class);

        System.out.println(answer.getId());
        System.out.println(answer.getUsage().getPromtTokens());
        System.out.println(answer.getChoices()[0].getMessage().getAnswer());
    }

    @BeforeEach
    public void setUp(){
        System.out.println("Starting Tests");
        startTime = System.nanoTime();
    }

    @AfterEach
    public void shutDown(){
        long endTime = System.nanoTime();
        System.out.println("Ausführungszeit: " + (endTime-startTime)/1000000 + "ms");
        System.out.println("*******");
    }

}
