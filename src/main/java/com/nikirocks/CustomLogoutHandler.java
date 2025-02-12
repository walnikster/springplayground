package com.nikirocks;

import com.nikirocks.user.UserDao;
import com.nikirocks.user.UserService;
import jakarta.persistence.SecondaryTable;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;


public class CustomLogoutHandler extends SimpleUrlLogoutSuccessHandler {

    private String logoutUrl;

    /**
     * An allowed callback URL.
     */
    private String logoutRedirectUrl;

    /**
     * The ID of your User Pool Client.
     */
    private String userPoolClientId;


    private UserService userService;
    /**
     * Here, we must implement the new logout URL request. We define what URL to send our request to, and set out client_id and logout_uri parameters.
     */
    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        return UriComponentsBuilder
                .fromUri(URI.create(logoutUrl))
                .queryParam("client_id", userPoolClientId)
                .queryParam("logout_uri", logoutRedirectUrl)
                .encode(StandardCharsets.UTF_8)
                .build()
                .toUriString();
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setLogoutRedirectUrl(String logoutRedirectUrl) {
        this.logoutRedirectUrl = logoutRedirectUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    public void setUserPoolClientId(String userPoolClientId) {
        this.userPoolClientId = userPoolClientId;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        DefaultOidcUser auth = (DefaultOidcUser) authentication.getPrincipal();
        userService.deleteUser(auth.getAttributes().get("username").toString());
        super.onLogoutSuccess(request, response, authentication);

    }
}


