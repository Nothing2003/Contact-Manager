package com.cm.cm2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping(value = "/home")
    public String home() {
        return "home";
    }

    @GetMapping(value = "/about")
    public String aboutPage(Model model) {
        model.addAttribute("isLogin", false);

        return "about";
    }

    @GetMapping(value = "/services")
    public String servicesPage() {
        return "services";
    }

    @GetMapping(value = "/contact")
    public String contactPage() {
        return "contact";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/register")
    public String registerPage() {
        return "register";
    }

}
