package com.nikirocks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication
@EnableWebSecurity
public class Main {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Main.class);

        application.run(args);
    }
}