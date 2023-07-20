package de.adesso.openaishowcase.Repositories;

import de.adesso.openaishowcase.Models.Mail;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
public class MailRepositoryTest {

    private long startTime;
    private static final Logger LOGGER = Logger.getLogger(MailRepositoryTest.class.getName());

    @Autowired
    MailRepository mailRepository;

    @BeforeEach
    public void setUp() {
        LOGGER.info("Starting Tests");
        startTime = System.nanoTime();
    }

    @AfterEach
    public void shutDown() {
        long endTime = System.nanoTime();
        LOGGER.info("Ausführungszeit: " + (endTime - startTime) / 1000000 + "ms");
        LOGGER.info("*******");
    }

    @Test
    public void findAllUncategorized_Test(){
        Mail mail1 = new Mail();
        Mail mail2 = new Mail();
        Mail mail3 = new Mail();
        mail3.setCategory("Newsletter");

        mailRepository.save(mail1);
        mailRepository.save(mail2);
        mailRepository.save(mail3);

        LOGGER.info(String.format("MailRepo: %s",mailRepository.findAll()));

        List<Mail> categorizedMails = mailRepository.findAllUncategorized();

        LOGGER.info(String.format("Uncategorized mails %s",categorizedMails));

        assertTrue("",categorizedMails.size()==2);
    }
}
