package de.adesso.openaishowcase.Utils;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

@SpringBootTest
public class StringUtilsTest {

    private final Logger logger = Logger.getLogger("Logger");

    @Test
    public void removePunctuation_Should_Return_String_Without_Punctuation_de(){
        String prompt = "Hallo, mein Name ist John Doe. Dies ist ein Test, um die Satzzeichen zu entfernen! Test123?";
        String should_return = "Hallo mein Name ist John Doe Dies ist ein Test um die Satzzeichen zu entfernen Test123";
        String real_return = StringUtils.removePunctuation(prompt);
        logger.info("Antwort: " + real_return);
        assertTrue("Satzzeichen wurden nicht richtig entfernt", real_return.equals(should_return));
    }

}
