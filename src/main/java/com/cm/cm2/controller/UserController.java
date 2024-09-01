package com.cm.cm2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cm.cm2.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userServiceimp;

    public UserController(UserService userServiceimp) {
        this.userServiceimp = userServiceimp;
    }

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value = "/dashbaord")
    public String userDashbaord() {
        return "user/dashbaord";
    }

    @GetMapping(value = "/profile")
    public String userProfile(Model model, Authentication authentication) {

        return "user/profile";
    }

}
