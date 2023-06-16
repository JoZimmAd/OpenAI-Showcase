package de.adesso.openaishowcase.OpenAIApi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.util.Assert.isTrue;

@SpringBootTest
public class ApiRequestTest {
    private long startTime;

    @Test
    public void askQuestion_Should_Not_Be_Empty() throws JSONException, ParseException {
        String prompt_en = "What is the content of the following E-Mail: Dear Mr. X, we tried to reach you because of your car insurance. Please call back as soon as possible. Kind Regards, Mrs. Y";
        String prompt_de = "Fasse die folgende E-Mail kurz zusammen: Sehr geehrter Herr X, wir haben versucht sie wegen Ihrer Autoversicherung zu erreichen. Bitte rufen Sie mich möglichst zeitnah zurück. Mit freundlichen Grüen, Mrs. Y";

        String answer = new ApiRequest().askQuestion(prompt_de);
        System.out.println(answer);
        isTrue(!answer.isEmpty(),"Received an Answer!");
    }

    @Test
    public void askQuestion_Should_Return_Positive_EMail() throws JSONException, ParseException, JsonProcessingException {
        String prompt_de = "Ist der folgende Text negativ, positiv oder neutral? Das Ergebnis ist sehr gut";
        String answerstring = new ApiRequest().askQuestion(prompt_de);
        ApiAnswer answer = new ObjectMapper().readValue(answerstring, ApiAnswer.class);
        System.out.println(answer.getChoices()[0].getMessage().getAnswer());
        isTrue(answer.getChoices()[0].getMessage().getAnswer().equals("Positiv."),"Answer was false categorized");
    }

    @Test
    public void askQuestion_Should_Return_Neutral_EMail() throws JSONException, ParseException, JsonProcessingException {
        String prompt_de = "Ist der folgende Text negativ, positiv oder neutral? Das Ergebnis ist ok";
        String answerstring = new ApiRequest().askQuestion(prompt_de);
        ApiAnswer answer = new ObjectMapper().readValue(answerstring, ApiAnswer.class);
        System.out.println(answer.getChoices()[0].getMessage().getAnswer());
        isTrue(answer.getChoices()[0].getMessage().getAnswer().equals("Neutral."),"Answer was false categorized");
    }

    @Test
    public void askQuestion_Should_Return_Negative_EMail() throws JSONException, ParseException, JsonProcessingException {
        String prompt_de = "Ist der folgende Text negativ, positiv oder neutral? Das Ergebnis ist schlecht";
        String answerstring = new ApiRequest().askQuestion(prompt_de);
        ApiAnswer answer = new ObjectMapper().readValue(answerstring, ApiAnswer.class);
        System.out.println(answer.getChoices()[0].getMessage().getAnswer());
        isTrue(answer.getChoices()[0].getMessage().getAnswer().equals("Negativ."),"Answer was false categorized");
    }

    @Test
    public void askQuestion_Filter_For_EMail_Topic() throws JSONException, ParseException, JsonProcessingException {
        String prompt_de = "Es ist folgende E-Mail gegeben: " +
                "Sehr geehrter Herr X, ich benötige für die Umsetzung bei meiner Tätigkeit als Entwickler Unterstützung " +
                "beim Aufsetzen einer Entwicklungsumgebung. Mit freundlichen Grüßen, Mr. Y" +
                "Welches der folgenden Themen beschreibt diese E-Mail? : {Einkaufen, Segeln, Supportanfrage, Heimwerken}";
        String answerstring = new ApiRequest().askQuestion(prompt_de);
        ApiAnswer answer = new ObjectMapper().readValue(answerstring, ApiAnswer.class);
        System.out.println(answer.getChoices()[0].getMessage().getAnswer());
        isTrue(answer.getChoices()[0].getMessage().getAnswer().equals("Supportanfrage"),"Answer was false categorized");
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
