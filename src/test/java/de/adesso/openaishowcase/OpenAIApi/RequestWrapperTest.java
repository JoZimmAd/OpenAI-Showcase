package de.adesso.openaishowcase.OpenAIApi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class RequestWrapperTest {

    @Test
    public void wrapTest(){
        String prompt = "Das ist ein Test.";
        System.out.println(new RequestWrapper().wrap(prompt));
    }
}
