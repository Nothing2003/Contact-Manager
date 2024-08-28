package com.cm.cm2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cm.cm2.helper.Helper;

@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value = "/dashbaord")
    public String userDashbaord() {
        return "user/dashbaord";
    }

    @GetMapping(value = "/profile")
    public String userProfile(Authentication authentication) {
        String userName = Helper.getEmailOfLoginUser( authentication);
        logger.info("User logger in:{}" + userName);
        return "user/profile";
    }

}
