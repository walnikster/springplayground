package com.nikirocks;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Component
@Profile(ProfileConfiguration.DEV)
public class PreAuthenticatedUserFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("PreAuthenticatedUserFilter");
        CustomUserDetails userDetails = new CustomUserDetails(
                "preauthUser",
                "{noop}",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")),
                "customValue"
        );




        PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(userDetails, "", userDetails.getAuthorities());

        authentication.setDetails(Map.of("username", "preauthUser"));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
