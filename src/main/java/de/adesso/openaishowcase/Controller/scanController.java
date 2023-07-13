package de.adesso.openaishowcase.Controller;

import de.adesso.openaishowcase.Mail.MailConnection;
import de.adesso.openaishowcase.OpenAIApi.ApiRequest;
import de.adesso.openaishowcase.Utils.MailUtils;
import jakarta.mail.Message;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class scanController {

    private final Logger logger = Logger.getLogger("Logger");

    @Autowired
    MailConnection con;
    @Autowired
    ApiRequest apiRequest;
    @PostMapping("/scan")
    public void doPost(HttpServletRequest request) throws Exception {
        con.connect(request.getParameter("email"),request.getParameter("password"));
        List<Message> messages = con.getAllMessages();
        for (Message m: messages){
            logger.info("Message: " + m.getSubject());
        }
        con.close();
    }
}
