package de.adesso.openaishowcase.OpenAIApi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.logging.Logger;

@SpringBootTest
public class RequestWrapperTest {

    private final Logger logger = Logger.getLogger("Logger");

    @Test
    public void wrapTest(){
        String prompt = "Das ist ein Test.";
        logger.info(new RequestWrapper().wrap(prompt));
    }
}
