package com.nikirocks;

import com.nikirocks.user.UserDao;
import com.nikirocks.user.UserEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CustomSuccessLoginHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private UserDao userDao;

    @Autowired
    public CustomSuccessLoginHandler(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        DefaultOidcUser auth = (DefaultOidcUser) authentication.getPrincipal();
        UserEntity user = new UserEntity();
        user.setUsername(auth.getAttributes().get("username").toString());
        user.setUserId(authentication.getName());
        userDao.save(user);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
