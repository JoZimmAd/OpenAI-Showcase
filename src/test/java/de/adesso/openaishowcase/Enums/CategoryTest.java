package de.adesso.openaishowcase.Enums;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.logging.Logger;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
public class CategoryTest {

    private long startTime;
    private static final Logger LOGGER = Logger.getLogger(CategoryTest.class.getName());

    @BeforeEach
    public void setUp(){
        LOGGER.info("Starting Tests");
        startTime = System.nanoTime();
    }

    @AfterEach
    public void shutDown(){
        long endTime = System.nanoTime();
        LOGGER.info("Ausführungszeit: " + (endTime-startTime)/1000000 + "ms");
        LOGGER.info("*******");
    }

    @Test
    public void getCategoriesAsString_Test(){
        List<Category> enumValues = new ArrayList<Category>(EnumSet.allOf(Category.class));
        LOGGER.info(enumValues.toString());
    }

    @Test
    public void validate_Test(){
        String answer = "Kategorie: NEWSLETTER";
        LOGGER.info(String.format("Category after validation: %s",Category.validate(answer)));
        assertTrue("",Category.validate(answer).equals("NEWSLETTER"));
    }
}
