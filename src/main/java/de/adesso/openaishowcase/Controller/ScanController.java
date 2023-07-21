package de.adesso.openaishowcase.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.adesso.openaishowcase.Enums.Category;
import de.adesso.openaishowcase.Enums.Mood;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static de.adesso.openaishowcase.Utils.MailUtils.getTextFromMessage;

@Controller
public class ScanController {

    public static final List<Category> CATEGORIES = new ArrayList<Category>(EnumSet.allOf(Category.class));
    private static final Logger LOGGER = Logger.getLogger(ScanController.class.getName());


    @Autowired
    private MailConnection connection;

    @Autowired
    private ApiRequest apiRequest;

    @Autowired
    private MailRepository mailRepository;

    @ModelAttribute("mailList")
    public List<Mail> mails() {
        return mailRepository.findAllByOrderByTimestampDesc();
    }

    @RequestMapping(value = "/mails", method = RequestMethod.GET)
    public ModelAndView mailsModelAndView(Mail mail) {
        ModelAndView mav = new ModelAndView("mails");
        mav.addObject("mailList", mailRepository.findAllByOrderByTimestampDesc());
        return mav;
    }

    @GetMapping("/fetch")
//    @Async
    public String fetchMails(@Value("${mail.imap.user}") String email,
                             @Value("${mail.imap.password}") String password,
                             @Value("${mail.imap.host}") String host) throws Exception {

        //Establish connection to mailserver
        connection = new MailConnection();
        connection.connect(host, email, password);

        //Fetch all messages and update in database
        List<Message> messageList = connection.getAllMessages();
        for (Message m : messageList) {
            Mail mail = new Mail(m.getFrom()[0].toString(),
                    m.getAllRecipients()[0].toString(),
                    m.getSentDate(),
                    getTextFromMessage(m),
                    m.getSubject(),
                    email);
            Optional<Mail> persisted = mailRepository.findByTimestamp(mail.getTimestamp());
            if (persisted.isEmpty()) {
                mailRepository.save(mail);
            }
        }
        LOGGER.info("Fetched all Mails and updated them in the database");
        connection.close();
        return "redirect:mails";
    }

    @GetMapping("/scan/{id}")
    public String scanSingleMail(@PathVariable Long id) throws JsonProcessingException, InterruptedException {
        String prompt = StringUtils.EMPTY;
        String answerString = StringUtils.EMPTY;

        Mail mailRes;
        Optional<Mail> mailOpt = mailRepository.findById(id);

        if (mailOpt.isPresent()) {
            mailRes = mailOpt.get();
            prompt = String.format("Kategorisiere den Inhalt folgender E-Mail nach vorgegebenen Kategorien und Stimmungen: " +
                            "Kategorien: %s, " +
                            "Stimmungen: %s, " +
                            "Inhalt der email: %s",
                    Arrays.toString(Category.values()),
                    Arrays.toString(Mood.values()),
                    mailRes.getText());

            prompt = MailUtils.replaceLineBreaks(prompt);
            answerString = apiRequest.askQuestion(prompt);
            ApiAnswer answer = new ObjectMapper().readValue(answerString, ApiAnswer.class);
            mailRes.setCategory(Category.validate(Category.validate(answer.getChoices()[0].getMessage().getAnswer())));
            mailRes.setMood(Mood.validate(Mood.validate(answer.getChoices()[0].getMessage().getAnswer())));
            mailRepository.save(mailRes);
            return "redirect:/mails";
        }
        return "redirect:/mails";
    }

    @PostMapping("/scan")
    public String scanMails() throws JsonProcessingException, InterruptedException {
        String prompt = StringUtils.EMPTY;
        String answerString = StringUtils.EMPTY;

        //Send all uncategorized mails to OpenAI
        //Insert new categorized mails in database
        List<Mail> persistedMails = mailRepository.findAllUncategorized();

        int counter = 0;
        for (Mail m : persistedMails) {
            if (counter >= 2) {
                Thread.sleep(21000);
                LOGGER.info("Sleeping for 21s...");
            }

            prompt = String.format("Kategorisiere den Inhalt folgender E-Mail nach vorgegebenen Kategorien und Stimmungen: " +
                            "Kategorien: %s, " +
                            "Stimmungen: %s, " +
                            "Inhalt der email: %s",
                    Arrays.toString(Category.values()),
                    Arrays.toString(Mood.values()),
                    m.getText());

            prompt = MailUtils.replaceLineBreaks(prompt);
            answerString = apiRequest.askQuestion(prompt);
            ApiAnswer answer = new ObjectMapper().readValue(answerString, ApiAnswer.class);
            String category = Category.validate(answer.getChoices()[0].getMessage().getAnswer());
            String mood = Mood.validate(answer.getChoices()[0].getMessage().getAnswer());
            m.setCategory(Category.validate(category));
            m.setMood(Mood.validate(mood));
            mailRepository.save(m);
            counter++;
        }
        return "redirect:mails";
    }
}
