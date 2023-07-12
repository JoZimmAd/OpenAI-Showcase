package de.adesso.openaishowcase.OpenAIApi;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;


@SpringBootTest
public class ApiAnswerTest {


    private long startTime;
    private final Logger logger = Logger.getLogger("Logger");


    @Test
    public void getApiKeyTest(){
        logger.info(new ApiRequest().getApiKey());
    }

    @Test
    public void should_return_id_attribute_of_JSONString_de() throws JsonProcessingException {

        String prompt_de = "Kategorisiere die Worte in folgendem Satz: AM Sonntag um 11 Uhr springt der quirrlige Fuchs über den faulen Hund.";

        String answerstring = new ApiRequest().askQuestion(prompt_de);
        ApiAnswer answer = new ObjectMapper().readValue(answerstring, ApiAnswer.class);

        logger.info(answer.getId());
        logger.info(String.valueOf(answer.getUsage().getPromtTokens()));
        logger.info(answer.getChoices()[0].getMessage().getAnswer());
    }

    @Test
    public void should_return_id_attribute_of_JSONString_en() throws JsonProcessingException {

        String prompt_en = "Categorize the words in the following sentence: At Sunday 11am, the quirky fox jumps over the lazy dog.";

        String answerstring = new ApiRequest().askQuestion(prompt_en);
        ApiAnswer answer = new ObjectMapper().readValue(answerstring, ApiAnswer.class);

        logger.info(answer.getId());
        logger.info(String.valueOf(answer.getUsage().getPromtTokens()));
        logger.info(answer.getChoices()[0].getMessage().getAnswer());
    }

    @BeforeEach
    public void setUp(){
        logger.info("Starting Tests");
        startTime = System.nanoTime();
    }

    @AfterEach
    public void shutDown(){
        long endTime = System.nanoTime();
        logger.info("Ausführungszeit: " + (endTime-startTime)/1000000 + "ms");
        logger.info("*******");
    }

}
