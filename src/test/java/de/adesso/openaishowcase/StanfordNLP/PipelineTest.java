package de.adesso.openaishowcase.StanfordNLP;

import edu.stanford.nlp.pipeline.CoreDocument;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;


@SpringBootTest
public class PipelineTest {

    private Pipeline pipeline;
    private final String prompt = "At Sunday 11am, the quirky fox jumps over the lazy dog. At Monday, the blue bird flies to Monaco to visit a grey goose.";
    @Test
    public void process_Should_Return_Coredocument(){
        pipeline = new Pipeline();
        CoreDocument doc = pipeline.process(prompt);

        System.out.println("*******");
        System.out.println("TEXT: "+doc.text());
        System.out.println("TOKENS: "+doc.tokens());
        System.out.println("ENTITIES "+doc.entityMentions());
        System.out.println("SENTENCES: "+doc.sentences());
        System.out.println("CORE OF CHAINS: "+doc.corefChains());

        Assert.notNull(pipeline,"Pipeline is Null");
        Assert.notNull(pipeline,"Result of pipeline is Null");
        Assert.isTrue(prompt.equals(doc.text()),"Text is different than prompt");
    }

    @BeforeEach
    public void setUp(){
        System.out.println("Starting Tests");
    }

    @AfterEach
    public void shutDown(){
        System.out.println("*******");
    }

}
