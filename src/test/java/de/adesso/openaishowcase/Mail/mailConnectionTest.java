package de.adesso.openaishowcase.Mail;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.util.Assert.isNull;
import static org.springframework.util.Assert.isTrue;

@SpringBootTest
public class mailConnectionTest {

    private long startTime;
    private final Logger logger = Logger.getLogger("Logger");

    @Autowired
    MailConnection con;

    @Test
    public void should_Return_NotAuthorized(){
        //TODO
    }

    @Test
    public void getAllMessages_Should_Return_All_Messages() throws MessagingException {
        con.setAuth("openaishowcase@gmx.de","DummyPass");
        List<Message> messageLst = con.getAllMessages();
        isTrue(!messageLst.isEmpty(), "messageList is empty");
        for (Message m : messageLst) {
            logger.info("Message: " + m.getSubject());
        }
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
