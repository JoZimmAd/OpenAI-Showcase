package de.adesso.openaishowcase.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConnectionConfig {

    @Value("${mail.imap.host}")
    String host;

    @Value("${mail.imap.user}")
    String user;

    @Value("${mail.imap.password}")
    String password;

    @Value("${API.key}")
    String apiKey;

    @Bean
    public String host(){return this.host;}

    @Bean
    public String user(){return this.user;}

    @Bean
    public String password(){return this.password;}

    @Bean
    public String apiKey(){return this.apiKey;}
}
