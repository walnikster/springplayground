package com.nikirocks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.nikirocks.ProfileConfiguration.PROD;


@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.setAdditionalProfiles(PROD);
                application.run(args);
    }
}