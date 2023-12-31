package de.adesso.openaishowcase.Mail;

import jakarta.mail.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;

@Component
public class MailConnection {



    @Value("${mail.imap.host}")
    private String host;

    private Properties properties = new Properties();
    private Session mailSession;
    private Store store;

    @Autowired
    public MailConnection(){
        properties.put("mail.imap.ssl.enable", "true");
        mailSession = Session.getInstance(properties, null);
    }

    public void connect(String host,String user, String pass) throws MessagingException {
        store = mailSession.getStore("imap");
        store.connect(host,user,pass);
    }

    public List<Message> getAllMessages() throws MessagingException {
        Folder inbox = store.getFolder("Inbox");
        inbox.open(Folder.READ_ONLY);
        List<Message> messageList = List.of(inbox.getMessages());

        return messageList;
    }

    public void close() throws MessagingException {
        store.close();
    }

    public String getHost(){
        return host;
    }

}
