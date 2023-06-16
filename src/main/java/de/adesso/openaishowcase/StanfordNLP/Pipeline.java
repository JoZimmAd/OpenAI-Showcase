/**
 * Annotator	        ar	zh	en	fr	de	hu	it	es
 * Tokenize / Segment   ✔	✔	✔	✔	 	✔	✔	✔
 * Sentence Split	    ✔	✔	✔	✔	✔	 	✔	✔
 * Part of Speech	    ✔	✔	✔	✔	✔	✔	✔	✔
 * Lemma	 	 	            ✔
 * Named Entities	 	    ✔	✔	✔	✔	✔	✔	✔
 * Constituency Parsing	 ✔	✔	✔	✔	 	✔	✔   ✔
 * Dependency Parsing	    ✔	✔	✔	✔	 	✔	✔
 * Sentiment Analysis	 	 	✔
 * Mention Detection	 	✔	✔
 * Coreference	 	        ✔	✔
 * Open IE	 	 	            ✔
 *
 *
 * see https://stanfordnlp.github.io/CoreNLP/human-languages.html
 */
package de.adesso.openaishowcase.StanfordNLP;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.simple.Sentence;

import java.util.List;
import java.util.Properties;

public class Pipeline {

    private Properties properties;
    public StanfordCoreNLP pipeline;
    public Pipeline(){
        properties = new Properties();
        properties.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        properties.setProperty("ner.useSUTime", "false");
        properties.setProperty("tokenize.language","de");
        this.pipeline = new StanfordCoreNLP(properties);
    }
    public CoreDocument process(String prompt){
        CoreDocument document = new CoreDocument(prompt);
        this.pipeline.annotate(document);
        return document;
    }

    public List<String> getNerTags(String prompt){
        Sentence sentence = new Sentence(prompt);
        return sentence.nerTags(properties);
    }
}
