package de.adesso.openaishowcase.Controller;

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
public class scanControllerTest {

    private long startTime;
    private final Logger logger = Logger.getLogger("Logger");

    MockMvc mockMvc;

    @InjectMocks
    ScanController scanController;

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

    @Test
    public void doPost_Test() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(scanController).build();

        MvcResult result = mockMvc.perform(post("/scan")
                        .param("email","openaishowcase@gmx.de")
                        .param("password", "DummyPass")
                ).andExpect(status().isOk())
                .andReturn();
    }
}
