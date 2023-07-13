package de.adesso.openaishowcase.Mail;

import de.adesso.openaishowcase.OpenAIApi.ApiRequest;
import de.adesso.openaishowcase.Utils.MailUtils;
import jakarta.mail.AuthenticationFailedException;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.util.Assert.isTrue;

@SpringBootTest
public class mailConnectionTest {

    private long startTime;
    private final Logger logger = Logger.getLogger("Logger");

    @Autowired
    MailConnection con;

    @Test
    public void should_Return_NotAuthorized(){
        Exception exception = assertThrows(AuthenticationFailedException.class, () -> {
            con.connect("Wrong","Login");
        });
    }

    @Test
    public void getAllMessages_Should_Return_All_Messages() throws Exception {
        con.connect("openaishowcase@gmx.de","DummyPass");
        List<Message> messageLst = con.getAllMessages();
        isTrue(!messageLst.isEmpty(), "messageList is empty");

        for (Message m : messageLst) {
            logger.info("Message: " + m.getSubject());
            logger.info("Text: " + MailUtils.getTextFromMessage(m));
        }
    }

    @Test
    public void close_Test() throws MessagingException {
        con.connect("openaishowcase@gmx.de","DummyPass");
        List<Message> messageLst = con.getAllMessages();
        isTrue(!messageLst.isEmpty(), "messageList is empty");
        con.close();
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            con.getAllMessages();
        });

    }

    @BeforeEach
    public void setUp() {
        logger.info("Starting Tests");
        startTime = System.nanoTime();
    }

    @AfterEach
    public void shutDown() {
        long endTime = System.nanoTime();
        logger.info("Ausführungszeit: " + (endTime - startTime) / 1000000 + "ms");
        logger.info("*******");
    }
}
