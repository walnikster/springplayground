package com.nikirocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
@Profile(ProfileConfiguration.DEV)
public class DevSecurityConfiguration {

    @Autowired
    private CustomSuccessLoginHandler customSuccessLoginHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, PreAuthenticatedUserFilter preAuthenticatedUserFilter) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)

                .addFilterBefore(preAuthenticatedUserFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().authenticated()).formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());



        return http.build();
    }
}
