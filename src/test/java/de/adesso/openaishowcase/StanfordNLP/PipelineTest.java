package de.adesso.openaishowcase.StanfordNLP;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.simple.Sentence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;
import java.util.logging.Logger;


@SpringBootTest
public class PipelineTest {

    private Pipeline pipeline;
    private final String prompt = "At Sunday 11am, the quirky fox jumps over the lazy dog. At Monday, the blue bird flies to Monaco to visit a grey goose.";
    private final String prompt_de = "Am Sonntag um 11 Uhr springt der schrullige Fuchs über den faulen Hund. Am Montag fliegt der blaue Vogel nach Monaco, um eine Graugans zu besuchen.";

    private final Logger logger = Logger.getLogger("Logger");
    private long startTime;
    @Test
    public void process_Should_Return_Coredocument(){
        pipeline = new Pipeline();
        CoreDocument doc = pipeline.process(prompt_de);

        logger.info("*******");
        logger.info("TEXT: "+doc.text());
        logger.info("TOKENS: "+doc.tokens());
        logger.info("ENTITIES "+doc.entityMentions());
        logger.info("SENTENCES: "+doc.sentences());
        logger.info("CORE OF CHAINS: "+doc.corefChains());

        Assert.notNull(pipeline,"Pipeline is Null");
        Assert.notNull(pipeline,"Result of pipeline is Null");
        Assert.isTrue(prompt_de.equals(doc.text()),"Text is different than prompt");
    }

    @Test
    public void getNerTags_Should_Return_List_of_Strings(){
        pipeline = new Pipeline();
        List<String> nerTags = pipeline.getNerTags(prompt);
        for (String s: nerTags) {
            logger.info(s);
        }
    }

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

}
