package de.adesso.openaishowcase.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.adesso.openaishowcase.Enums.Category;
import de.adesso.openaishowcase.Mail.MailConnection;
import de.adesso.openaishowcase.Models.Mail;
import de.adesso.openaishowcase.OpenAIApi.ApiAnswer;
import de.adesso.openaishowcase.OpenAIApi.ApiRequest;
import de.adesso.openaishowcase.Repositories.MailRepository;
import de.adesso.openaishowcase.Utils.MailUtils;
import jakarta.mail.Message;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.logging.Logger;

import static de.adesso.openaishowcase.Utils.MailUtils.getTextFromMessage;

@RestController
public class ScanController {

    public static final List<Category> CATEGORIES = new ArrayList<Category>(EnumSet.allOf(Category.class));
    private static final Logger LOGGER = Logger.getLogger(ScanController.class.getName());


    @Autowired
    private MailConnection connection;

    @Autowired
    private ApiRequest apiRequest;

    @Autowired
    private MailRepository mailRepository;


    @GetMapping("/fetch")
    @Async
    public void fetchMails(@Value("${mail.imap.user}") String email,
                             @Value("${mail.imap.password}") String password,
                             @Value("${mail.imap.host}") String host) throws Exception {

        //Establish connection to mailserver
        connection = new MailConnection();
        connection.connect(host,email,password);

        //Fetch all messages and update in database
        List<Message> messageList = connection.getAllMessages();
        for (Message m : messageList){
            Mail mail = new Mail(m.getFrom().toString(), m.getAllRecipients().toString(), m.getSentDate(), getTextFromMessage(m));
            mailRepository.save(mail);
        }
        LOGGER.info("Fetched all Mails and updated them in the database");
        connection.close();
    }

    @PostMapping("/scan")
    @Async
    public void scanMails() throws JsonProcessingException, InterruptedException {
        String prompt = StringUtils.EMPTY;
        String answerString = StringUtils.EMPTY;

        //Send all uncategorized mails to OpenAI
        //Insert new categorized mails in database
        for (Mail m : mailRepository.findAllUncategorized()) {
            LOGGER.info(String.format("Categorizing mail: %s",m.getText()));
            prompt = String.format("Kategorisiere folgende E-Mail nach den Kategorien %s %s", CATEGORIES.toString(), m.getText());
            prompt = MailUtils.replaceLineBreaks(prompt);
            answerString = apiRequest.askQuestion(prompt);
            ApiAnswer answer = new ObjectMapper().readValue(answerString, ApiAnswer.class);
            String returnedAnswer = Category.validate(answer.getChoices()[0].getMessage().getAnswer());
            m.setCategory(Category.validate(returnedAnswer));
            mailRepository.save(m);
            LOGGER.info(String.format("Category is: %s",returnedAnswer));
            LOGGER.info("Sleeping for 21s...");
            Thread.sleep(21000);
        }
    }
}
