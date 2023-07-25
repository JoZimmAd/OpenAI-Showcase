package de.adesso.openaishowcase.Controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.logging.Logger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
public class MailControllerTest {

    private long startTime;
    private static final Logger LOGGER = Logger.getLogger(MailControllerTest.class.getName());

    MockMvc mockMvc;

    @InjectMocks
    MailController scanController;

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
}
