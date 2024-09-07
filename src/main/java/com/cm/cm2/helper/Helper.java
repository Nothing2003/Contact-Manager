package com.cm.cm2.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

public class Helper {

    public static String getEmailOfLoginUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();

        if (principal instanceof OAuth2AuthenticatedPrincipal oAuth2AuthenticatedPrincipal) {
            OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            String clientId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
            if (clientId.equalsIgnoreCase("google")) {
                System.out.println("Getting email from Google");
                return oAuth2AuthenticatedPrincipal.getAttribute("email");
            } else if (clientId.equalsIgnoreCase("github")) {
                System.out.println("Getting email from GitHub");
                String email = oAuth2AuthenticatedPrincipal.getAttribute("email");
                String login = oAuth2AuthenticatedPrincipal.getAttribute("login");
                return email != null ? email : login + "@gmail.com";
            }
        } else if (principal instanceof UserDetails userDetails) {
            System.out.println("Getting email from regular authentication");
            return userDetails.getUsername();
        } else {
            System.out.println("Unknown principal type. ");
        }

        return "";
    }

    public static String getLinkForEmailVarification(String emailToken) {
        String link = "http://localhost:8080/auth/varify-email?token=" + emailToken;
        // String link = "http://contactmanager.ap-south-1.elasticbeanstalk.com/auth/varify-email?token=" + emailToken;
        return link;
    }

    public static String getLinkForForgetPasswoed(String Token) {
        String link = "http://localhost:8080/auth/changePassword?token=" + Token;
        // String link = "http://contactmanager.ap-south-1.elasticbeanstalk.com/auth/changePassword?token=" + Token;
        return link;
    }
}
