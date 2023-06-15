package de.adesso.openaishowcase.StanfordNLP;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.Properties;

public class Pipeline {

    private Properties properties;
    public StanfordCoreNLP pipeline;
    public Pipeline(){
        properties = new Properties();
        properties.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        properties.setProperty("ner.useSUTime", "false");
        this.pipeline = new StanfordCoreNLP(properties);
    }

    public CoreDocument process(String prompt){
        CoreDocument document = new CoreDocument(prompt);
        this.pipeline.annotate(document);
        return document;
    }
}
