package com.cm.cm2.config;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.cm.cm2.entities.Providers;
import com.cm.cm2.entities.User;
import com.cm.cm2.helper.AppCon;
import com.cm.cm2.repsitories.UserRepo;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenicationScuccessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(OAuthAuthenicationScuccessHandler.class);
    @Autowired
    private UserRepo userRepo;

    @SuppressWarnings("null")
    @Override

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        logger.info("OAuthAuthenicationScuccessHandler");
        var oAuthAuthenicationToken = (OAuth2AuthenticationToken) authentication;
        String authorizedClientRegistrationId = oAuthAuthenicationToken.getAuthorizedClientRegistrationId();
        logger.info(authorizedClientRegistrationId);
        var oathUser = (DefaultOAuth2User) authentication.getPrincipal();
        oathUser.getAttributes().forEach((key, value) -> {
            logger.info(key + ":" + value);
        });
        User user1 = new User();
        user1.setUserId(UUID.randomUUID().toString());
        user1.setRoleList(List.of(AppCon.rUser));

        if (authorizedClientRegistrationId.equalsIgnoreCase("google")) {

            user1.setEmail(oathUser.getAttribute("email").toString());
            user1.setName(oathUser.getAttribute("name").toString());
            user1.setProfilePic(oathUser.getAttribute("picture").toString());
            user1.setPassword(
                    "password"
            );
            user1.setProvider(Providers.Google);
            user1.setEnable(true);
            user1.setEmailVefied(true);
            user1.setProviderUserId(oathUser.getName());
            user1.setAbout("It creating using google..");
            User user2 = userRepo.findByEmail(oathUser.getAttribute("email").toString()).orElse(null);
            if (user2 == null) {
                userRepo.save(user1);
                logger.info("User Saved : " + oathUser.getAttribute("email").toString());
            }

        } else if (authorizedClientRegistrationId.equalsIgnoreCase("github")) {
            String email = oathUser.getAttribute("email");
            String login = oathUser.getAttribute("login");
            String avatarUrl = oathUser.getAttribute("avatar_url");

            user1.setEmail(email != null ? email : login + "@gmail.com");
            user1.setPassword("password");
            user1.setProfilePic(avatarUrl != null ? avatarUrl : "defaultAvatarUrl");
            user1.setName(login != null ? login : "defaultLoginName");
            user1.setProvider((Providers.GITHUB));
            user1.setEnable(true);
            user1.setEmailVefied(true);
            user1.setProviderUserId(oathUser.getName());
            user1.setAbout("It was created using GitHub.");

            User existingUser = userRepo.findByEmail(email != null ? email : login + "@gmail.com").orElse(null);
            if (existingUser == null) {
                userRepo.save(user1);
                logger.info("User Saved: " + email);
            }
        }
        /*
       
             
         */
        response.sendRedirect("/user/profile");
    }
}
