package com.nikirocks;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
@Profile(ProfileConfiguration.DEV)
public class AuthenticationSuccessEventHandler implements ApplicationListener<AuthenticationSuccessEvent> {
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        System.out.println("Authentication Success with principal: " + event.getAuthentication().getPrincipal());
    }
}
