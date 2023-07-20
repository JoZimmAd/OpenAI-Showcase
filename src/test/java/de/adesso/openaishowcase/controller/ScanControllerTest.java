package de.adesso.openaishowcase.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.logging.Logger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ScanControllerTest {

    private long startTime;
    private static final Logger LOGGER = Logger.getLogger(ScanControllerTest.class.getName());

    MockMvc mockMvc;

    @InjectMocks
    ScanController scanController;

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
