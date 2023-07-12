package de.adesso.openaishowcase.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConnectionConfig {

    @Value("${mail.imap.host}")
    private String host;

    @Value("${mail.imap.user}")
    private String user;

    @Value("${mail.imap.password}")
    private String password;

    @Bean
    public String host(){return this.host;}

    @Bean
    public String user(){return this.user;}

    @Bean
    public String password(){return this.password;}
}
