package com.nikirocks;

import com.nikirocks.user.UserDao;
import com.nikirocks.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@Profile(ProfileConfiguration.PROD)
public class SecurityConfiguration {


    @Value("${aws.cognito.logout.success.redirectUrl}")
    private String logoutRedirectUrl;

    @Value("${aws.cognito.logoutUrl}")
    private String logoutUrl;

    @Value("${spring.security.oauth2.client.registration.cognito.client-id}")
    private String clientId;
    @Autowired
    private CustomSuccessLoginHandler customSuccessLoginHandler;
    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        CustomLogoutHandler cognitoLogoutHandler = new CustomLogoutHandler();
        cognitoLogoutHandler.setUserService(userService);
        cognitoLogoutHandler.setLogoutUrl(logoutUrl);
        cognitoLogoutHandler.setLogoutRedirectUrl(logoutRedirectUrl);
        cognitoLogoutHandler.setUserPoolClientId(clientId);

        http.csrf(Customizer.withDefaults())
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/webjars/**").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/js/**").permitAll()
                        .requestMatchers("/images/**").permitAll()
                        .requestMatchers("/monitoring").hasRole("user")
                        .requestMatchers("/secured").authenticated()
                        .anyRequest()
                        .authenticated())
                .oauth2Login((oath2Login) -> oath2Login.successHandler(customSuccessLoginHandler).userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userAuthoritiesMapper(userAuthoritiesMapper())))
                .logout(logout -> logout.logoutSuccessHandler(cognitoLogoutHandler));
        
        return http.build();
    }

    @Bean
    public GrantedAuthoritiesMapper userAuthoritiesMapper() {
        return (authorities) -> {
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
            Optional<OidcUserAuthority> awsAuthority = (Optional<OidcUserAuthority>) authorities.stream()
                    .filter(grantedAuthority -> "OIDC_USER".equals(grantedAuthority.getAuthority())).findFirst();
            if (awsAuthority.isPresent()) {
                mappedAuthorities = ((List<String>) awsAuthority.get().getAttributes().get("cognito:groups")).stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toSet());
            }
            return mappedAuthorities;
        };
    }

}

