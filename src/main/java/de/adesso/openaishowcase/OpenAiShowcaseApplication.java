package de.adesso.openaishowcase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
@EnableAsync
public class OpenAiShowcaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenAiShowcaseApplication.class, args);
    }

}
