package de.adesso.openaishowcase.StanfordNLP;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.simple.Sentence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;


@SpringBootTest
public class PipelineTest {

    private Pipeline pipeline;
    private final String prompt = "At Sunday 11am, the quirky fox jumps over the lazy dog. At Monday, the blue bird flies to Monaco to visit a grey goose.";
    private final String prompt_de = "Am Sonntag um 11 Uhr springt der schrullige Fuchs über den faulen Hund. Am Montag fliegt der blaue Vogel nach Monaco, um eine Graugans zu besuchen.";

    private long startTime;
    @Test
    public void process_Should_Return_Coredocument(){
        pipeline = new Pipeline();
        CoreDocument doc = pipeline.process(prompt_de);

        System.out.println("*******");
        System.out.println("TEXT: "+doc.text());
        System.out.println("TOKENS: "+doc.tokens());
        System.out.println("ENTITIES "+doc.entityMentions());
        System.out.println("SENTENCES: "+doc.sentences());
        System.out.println("CORE OF CHAINS: "+doc.corefChains());

        Assert.notNull(pipeline,"Pipeline is Null");
        Assert.notNull(pipeline,"Result of pipeline is Null");
        Assert.isTrue(prompt_de.equals(doc.text()),"Text is different than prompt");
    }

    @Test
    public void getNerTags_Should_Return_List_of_Strings(){
        pipeline = new Pipeline();
        List<String> nerTags = pipeline.getNerTags(prompt);
        System.out.println(nerTags);
    }

    @BeforeEach
    public void setUp(){
        System.out.println("Starting Tests");
        startTime = System.nanoTime();
    }

    @AfterEach
    public void shutDown(){
        long endTime = System.nanoTime();
        System.out.println("Ausführungszeit: " + (endTime-startTime)/1000000 + "ms");
        System.out.println("*******");
    }

}
