package de.adesso.openaishowcase.Controller;

import de.adesso.openaishowcase.Mail.MailConnection;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class scanController {

    private final Logger logger = Logger.getLogger("Logger");

    @Autowired
    MailConnection con;
    @PostMapping("/scan")
    public void doPost(HttpServletRequest request) throws MessagingException, IOException {
        con.connect(request.getParameter("email"),request.getParameter("password"));
        List<Message> messages = con.getAllMessages();
        for (Message m: messages){
            logger.info("Message: " + m.getSubject());
        }
        con.close();
    }
}
