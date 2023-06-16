package de.adesso.openaishowcase.OpenAIApi;

import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class ApiRequestTest {

    @Test
    public void askQuestion_Should_Not_Be_Empty() throws JSONException, ParseException {
        String prompt_en = "What is the content of the following E-Mail: Dear Mr. X, we tried to reach you because of your car insurance. Please call back as soon as possible. Kind Regards, Mrs. Y";
        String prompt_de = "Fasse die folgende E-Mail kurz zusammen: Sehr geehrter Herr X, wir haben versucht sie wegen Ihrer Autoversicherung zu erreichen. Bitte rufen Sie mich möglichst zeitnah zurück. Mit freundlichen Grüen, Mrs. Y";
        String answer = new ApiRequest().askQuestion(prompt_de);
        System.out.println(answer);
        Assert.isTrue(!answer.isEmpty(),"Received an Answer!");
    }
}
