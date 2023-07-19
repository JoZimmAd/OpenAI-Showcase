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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

import static de.adesso.openaishowcase.Utils.MailUtils.getTextFromMessage;

@Controller
@RequiredArgsConstructor
public class ScanController {

    private final MailConnection con;
    private final ApiRequest apiRequest;
    private final MailRepository mailRepository;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("mailList", mailRepository.findAll());
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView messages() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("mailList", mailRepository.findAll());
        return mav;
    }

    @PostMapping("/scan")
    public void scan(HttpServletRequest request) throws Exception {
        con.connect("imap.gmx.com", request.getParameter("email"), request.getParameter("password"));

        List<Message> messageList = con.getAllMessages();

        for (Message m : messageList) {
            String prompt = "Kategorisiere folgende E-Mail nach den Kategorien " + Arrays.toString(Category.values()) + " " + getTextFromMessage(m);
            prompt = MailUtils.replaceLineBreaks(prompt);
            String answerString = apiRequest.askQuestion(prompt);
            ApiAnswer answer = new ObjectMapper().readValue(answerString, ApiAnswer.class);
            String returnedAnswer = answer.getChoices()[0].getMessage().getAnswer();
            mailRepository.save(new Mail(m.getFrom().toString(), m.getAllRecipients().toString(), m.getSentDate(), returnedAnswer));
        }

        con.close();
    }
}
