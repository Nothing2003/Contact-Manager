package com.cm.cm2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.cm.cm2.entities.User;
import com.cm.cm2.helper.Helper;
import com.cm.cm2.services.UserService;

@ControllerAdvice
public class RootController {

    @Autowired
    private final UserService userServiceimp;

    RootController(UserService userServiceimp) {
        this.userServiceimp = userServiceimp;
    }

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @ModelAttribute
    public void addLoggedInUserInfo(Model model, Authentication authentication) {
        if(authentication==null){
            return ;
        }
        String userName = Helper.getEmailOfLoginUser(authentication);
        logger.info(userName);
        User user = userServiceimp.getUserByEmail(userName);
        if (user == null) {
            model.addAttribute("loggedInUser", null);
        } else {

            model.addAttribute("loggedInUser", user);
        }
    }
}
