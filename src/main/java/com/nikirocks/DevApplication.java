package com.nikirocks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.nikirocks.ProfileConfiguration.*;

@SpringBootApplication
public class DevApplication {


    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(com.nikirocks.Application.class);
        application.setAdditionalProfiles(DEV);
        application.run(args);
    }
}
