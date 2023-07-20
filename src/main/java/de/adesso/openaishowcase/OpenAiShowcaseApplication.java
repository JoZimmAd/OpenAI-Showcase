package de.adesso.openaishowcase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class OpenAiShowcaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenAiShowcaseApplication.class, args);
    }

}
