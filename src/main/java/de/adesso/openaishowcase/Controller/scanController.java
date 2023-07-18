package de.adesso.openaishowcase.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.adesso.openaishowcase.Enums.Category;
import de.adesso.openaishowcase.Mail.MailConnection;
import de.adesso.openaishowcase.Models.Mail;
import de.adesso.openaishowcase.OpenAIApi.ApiAnswer;
import de.adesso.openaishowcase.OpenAIApi.ApiRequest;
import de.adesso.openaishowcase.Repositories.MailRepository;
import de.adesso.openaishowcase.Utils.MailUtils;
import jakarta.mail.Message;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.logging.Logger;

import static de.adesso.openaishowcase.Utils.MailUtils.getTextFromMessage;

@RestController
public class scanController {

    private final Logger logger = Logger.getLogger("Logger");

    @Autowired
    MailConnection con;

    @Autowired
    ApiRequest apiRequest;

    @Autowired
    MailRepository mailRepository;
    @PostMapping("/scan")
    public void doPost(HttpServletRequest request) throws Exception {
        List<Category> enumValues = new ArrayList<Category>(EnumSet.allOf(Category.class));

        con = new MailConnection();
        con.connect("imap.gmx.com",request.getParameter("email"),request.getParameter("password"));


        List<Message> messageList = con.getAllMessages();

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

        logger.info("Persisted mails: " + mailRepository.findAll().toString());
        con.close();
    }
}
