package com.hugsmaker.MySecondApp;

import com.hugsmaker.MySecondApp.bored.model.Activity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MySecondAppApplication {
    private static final Logger log = LoggerFactory.getLogger(MySecondAppApplication.class);

    @Value("${api.url}")
    private String apiURL;

    public static void main(String[] args) {
        log.info("In main method");
        SpringApplication.run(MySecondAppApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return results -> {
            Activity activity = restTemplate.getForObject(apiURL,
                    Activity.class);
            log.info(activity.toString());
        };
    }
}
