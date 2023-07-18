package de.adesso.openaishowcase.OpenAIApi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.adesso.openaishowcase.Enums.Category;
import de.adesso.openaishowcase.Mail.MailConnection;
import de.adesso.openaishowcase.Models.Mail;
import de.adesso.openaishowcase.Repositories.MailRepository;
import de.adesso.openaishowcase.Utils.MailUtils;
import de.adesso.openaishowcase.Utils.StringUtils;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import org.apache.tomcat.util.json.ParseException;
import org.apache.xalan.res.XSLTErrorResources_en;
import org.json.JSONException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.logging.Logger;

import static de.adesso.openaishowcase.Utils.MailUtils.getTextFromMessage;
import static org.springframework.util.Assert.isTrue;

@SpringBootTest
public class ApiRequestTest {
    private long startTime;
    private final Logger logger = Logger.getLogger("Logger");

    @Autowired
    private ApiRequest apiRequest;

    @Autowired
    private MailConnection con;

    @Autowired
    private MailRepository mailRepository;

    @Test
    public void askQuestion_Should_Not_Be_Empty_de() throws JSONException, ParseException {
        String prompt_de = "Fasse die folgende E-Mail kurz zusammen: Sehr geehrter Herr X, wir haben versucht sie wegen Ihrer Autoversicherung zu erreichen. Bitte rufen Sie mich möglichst zeitnah zurück. Mit freundlichen Grüen, Mrs. Y";

        String answer = apiRequest.askQuestion(prompt_de);
        logger.info(answer);
        isTrue(!answer.isEmpty(),"Received no Answer!");
    }

    @Test
    public void askQuestion_Should_Not_Be_Empty_en() throws JSONException, ParseException {
        String prompt_en = "What is the content of the following E-Mail: Dear Mr. X, we tried to reach you because of your car insurance. Please call back as soon as possible. Kind Regards, Mrs. Y";

        String answer = apiRequest.askQuestion(prompt_en);
        logger.info(answer);
        isTrue(!answer.isEmpty(),"Received no Answer!");
    }

    @Test
    public void askQuestion_Should_Return_Positive_EMail_de() throws JsonProcessingException {
        String prompt_de = "Ist der folgende Text negativ, positiv oder neutral? Das Ergebnis ist sehr gut";

        String answerstring = apiRequest.askQuestion(prompt_de);
        ApiAnswer answer = new ObjectMapper().readValue(answerstring, ApiAnswer.class);
        logger.info(answer.getChoices()[0].getMessage().getAnswer());
        isTrue(answer.getChoices()[0].getMessage().getAnswer().equals("Positiv."),"Answer was false categorized");
    }
    @Test
    public void askQuestion_Should_Return_Positive_EMail_en() throws JsonProcessingException {
        String prompt_en = "Is the following text negative, positive or neutral? The result is very good";

        String answerstring = apiRequest.askQuestion(prompt_en);
        ApiAnswer answer = new ObjectMapper().readValue(answerstring, ApiAnswer.class);
        logger.info(answer.getChoices()[0].getMessage().getAnswer());
        isTrue(answer.getChoices()[0].getMessage().getAnswer().equals("Positiv."),"Answer was false categorized");
    }

    @Test
    public void askQuestion_Should_Return_Neutral_EMail_de() throws JsonProcessingException {
        String prompt_de = "Ist der folgende Text negativ, positiv oder neutral? Das Ergebnis ist ok";

        String answerstring = apiRequest.askQuestion(prompt_de);
        ApiAnswer answer = new ObjectMapper().readValue(answerstring, ApiAnswer.class);
        logger.info(answer.getChoices()[0].getMessage().getAnswer());
        isTrue(answer.getChoices()[0].getMessage().getAnswer().equals("Neutral."),"Answer was false categorized");
    }
    @Test
    public void askQuestion_Should_Return_Neutral_EMail_en() throws JSONException, ParseException, JsonProcessingException {
        String prompt_en = "Is the following text negative, positive or neutral? The result is ok";

        String answerstring = apiRequest.askQuestion(prompt_en);
        ApiAnswer answer = new ObjectMapper().readValue(answerstring, ApiAnswer.class);
        logger.info(answer.getChoices()[0].getMessage().getAnswer());
        isTrue(answer.getChoices()[0].getMessage().getAnswer().equals("Neutral."),"Answer was false categorized");
    }

    @Test
    public void askQuestion_Should_Return_Negative_EMail_de() throws JSONException, ParseException, JsonProcessingException {
        String prompt_de = "Ist der folgende Text negativ, positiv oder neutral? Das Ergebnis ist schlecht";

        String answerstring = apiRequest.askQuestion(prompt_de);
        ApiAnswer answer = new ObjectMapper().readValue(answerstring, ApiAnswer.class);
        logger.info(answer.getChoices()[0].getMessage().getAnswer());
        isTrue(answer.getChoices()[0].getMessage().getAnswer().equals("Negativ."),"Answer was false categorized");
    }
    @Test
    public void askQuestion_Should_Return_Negative_EMail_en() throws JSONException, ParseException, JsonProcessingException {
        String prompt_en = "Is the following text negative, positive or neutral? The result is bad";

        String answerstring = apiRequest.askQuestion(prompt_en);
        ApiAnswer answer = new ObjectMapper().readValue(answerstring, ApiAnswer.class);
        logger.info(answer.getChoices()[0].getMessage().getAnswer());
        isTrue(answer.getChoices()[0].getMessage().getAnswer().equals("Negativ."),"Answer was false categorized");
    }

    @Test
    public void askQuestion_Filter_For_EMail_Topic_de() throws JSONException, ParseException, JsonProcessingException {
        String prompt_de = "Es ist folgende E-Mail gegeben: " +
                "Sehr geehrter Herr X, ich benötige für die Umsetzung bei meiner Tätigkeit als Entwickler Unterstützung " +
                "beim Aufsetzen einer Entwicklungsumgebung. Mit freundlichen Grüßen, Mr. Y" +
                "Welches der folgenden Themen beschreibt diese E-Mail? : {Einkaufen, Segeln, Supportanfrage, Heimwerken}";

        String answerstring = apiRequest.askQuestion(prompt_de);
        ApiAnswer answer = new ObjectMapper().readValue(answerstring, ApiAnswer.class);
        logger.info(answer.getChoices()[0].getMessage().getAnswer());
        isTrue(answer.getChoices()[0].getMessage().getAnswer().equals("Supportanfrage"),"Answer was false categorized");
    }

    @Test
    public void askQuestion_Filter_For_EMail_Topic_en() throws JsonProcessingException {
        String prompt_en = "The following email was given: " +
                "Dear Mr. X, I need support for the implementation of my job as a developer " +
                "when setting up a development environment. Sincerely, Mr. Y" +
                "Which of the following topics does this email describe? : {shopping, sailing, support request, home improvement}";

        String answerstring = apiRequest.askQuestion(prompt_en);
        ApiAnswer answer = new ObjectMapper().readValue(answerstring, ApiAnswer.class);
        logger.info(answer.getChoices()[0].getMessage().getAnswer());
        isTrue(answer.getChoices()[0].getMessage().getAnswer().equals("support request"),"Answer was false categorized");
    }

    /**
     * This test should return the same answer for both prompts, either with or without punctuation. Removing punctuation from the prompt
     * saves tokens at the API. Every saved token in the input prompt equals around 0,003$
     * @throws JsonProcessingException
     */
    @Test
    public void askQuestion_Prompt_Without_Punctuation_Should_Return_Same_Answer_de() throws JsonProcessingException {
        String prompt_de = "Es ist folgende E-Mail gegeben: " +
        "Sehr geehrter Herr X, ich benötige für die Umsetzung bei meiner Tätigkeit als Entwickler Unterstützung " +
                "beim Aufsetzen einer Entwicklungsumgebung. Mit freundlichen Grüßen, Mr. Y" +
                "Welches der folgenden Themen beschreibt diese E-Mail? : {Einkaufen, Segeln, Supportanfrage, Heimwerken}";

        String answerstring = apiRequest.askQuestion(prompt_de);
        ApiAnswer answer = new ObjectMapper().readValue(answerstring, ApiAnswer.class);
        String returnedAnswer = answer.getChoices()[0].getMessage().getAnswer();
        logger.info("Antwort ohne Satzzeichenentfernung: " + returnedAnswer);

        String answerstring2 = apiRequest.askQuestion(StringUtils.removePunctuation(prompt_de));
        ApiAnswer answer2 = new ObjectMapper().readValue(answerstring2, ApiAnswer.class);
        String returnedAnswer2 = answer2.getChoices()[0].getMessage().getAnswer();
        logger.info("Antwort mit Satzzeichenentfernung: " + returnedAnswer2);

        isTrue(returnedAnswer.equals(returnedAnswer2),"Antworten stimmen nicht überein");
    }

    @Test
    public void askQuestion_Should_Return_Categorized_Response() throws Exception {

        con.connect("imap.gmx.com","openaishowcase@gmx.de","DummyPass");
        List<Message> messageList = con.getAllMessages();
        List<Category> enumValues = new ArrayList<Category>(EnumSet.allOf(Category.class));

        for (Message m : messageList){
            String prompt = "Kategorisiere folgende E-Mail nach den Kategorien " + enumValues.toString() + " " + getTextFromMessage(m);
            prompt = MailUtils.replaceLineBreaks(prompt);
            logger.info("Message Body: " + prompt);
            String answerString = apiRequest.askQuestion(prompt);
            ApiAnswer answer = new ObjectMapper().readValue(answerString, ApiAnswer.class);
            String returnedAnswer = answer.getChoices()[0].getMessage().getAnswer();
            mailRepository.save(new Mail(m.getFrom().toString(),m.getAllRecipients().toString(), m.getSentDate() ,returnedAnswer));
            logger.info("Answer: " + returnedAnswer);
            Thread.sleep(21000);
        }
        con.close();
        logger.info("Repo: " + mailRepository.findAll());
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
