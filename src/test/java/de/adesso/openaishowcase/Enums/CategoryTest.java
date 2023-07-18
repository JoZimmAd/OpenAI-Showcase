package de.adesso.openaishowcase.Enums;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.logging.Logger;

@SpringBootTest
public class CategoryTest {

    private long startTime;
    private final Logger logger = Logger.getLogger("Logger");

    @BeforeEach
    public void setUp(){
        logger.info("Starting Tests");
        startTime = System.nanoTime();
    }

    @AfterEach
    public void shutDown(){
        long endTime = System.nanoTime();
        logger.info("Ausführungszeit: " + (endTime-startTime)/1000000 + "ms");
        logger.info("*******");
    }

    @Test
    public void getCategoriesAsString_Test(){
        List<Category> enumValues = new ArrayList<Category>(EnumSet.allOf(Category.class));
        logger.info(enumValues.toString());
    }
}
