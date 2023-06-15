package de.adesso.openaishowcase.OpenAIApi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class ApiRequestTest {

    @Test
    public void askQuestion_Should_Return_Answer(){


        String prompt = "What is the content of the following E-Mail: Dear Mr. X, we tried to reach you because of your car insurance. Please call back as soon as possible. Kind Regards, Mrs. Y";

        String answer = new ApiRequest().askQuestion(prompt);

        System.out.println(answer);
        Assert.isTrue(!answer.isEmpty(),"Received an Answer!");
    }
}
