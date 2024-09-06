package com.cm.cm2.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cm.cm2.services.UserService;
import com.cm.cm2.entities.User;
import com.cm.cm2.helper.Message;
import com.cm.cm2.helper.MessageType;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userServiceimp;

    public UserController(UserService userServiceimp) {
        this.userServiceimp = userServiceimp;

    }

    @GetMapping(value = "/dashbaord")
    public String userDashbaord() {
        return "user/dashbaord";
    }

    @GetMapping(value = "/profile")
    public String userProfile(Model model, Authentication authentication) {

        return "user/profile";

    }

    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") String id, HttpSession session) {
        System.out.println("User id is " + id);
        User user = userServiceimp.getUserById(id).orElse(null);
        if (user.getUserId().equalsIgnoreCase(id)) {
            userServiceimp.deleteUser(id);

            session.setAttribute("message", Message.builder().contant("Your account successfully deleted.").type(MessageType.red).build());
            return "redirect:/login";
        }
        session.setAttribute("message", Message.builder().contant("User Not found").type(MessageType.red).build());
        return "error_page";
    }

}
