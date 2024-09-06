package com.cm.cm2.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cm.cm2.entities.User;
import com.cm.cm2.forms.UserForm;
import com.cm.cm2.helper.Message;
import com.cm.cm2.helper.MessageType;
import com.cm.cm2.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {

    @Autowired
    private final UserService userService;

    public PageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

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

    // @PostMapping(value = "/login")
    // public String loginPagepost() {
    //     return "login";
    // }
    @GetMapping(value = "/register")
    public String registerPage(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }

    @PostMapping("/doRegister")
    public String doRegisterHandaler(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult, HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "register";
        }
        User user = new User();
        BeanUtils.copyProperties(userForm, user);
        user.setEnable(false);
        user.setProfilePic("https://th.bing.com/th/id/R.19fa7497013a87bd77f7adb96beaf768?rik=144XvMigWWj2bw&riu=http%3a%2f%2fwww.pngall.com%2fwp-content%2fuploads%2f5%2fUser-Profile-PNG-High-Quality-Image.png&ehk=%2bat%2brmqQuJrWL609bAlrUPYgzj%2b%2f7L1ErXRTN6ZyxR0%3d&risl=&pid=ImgRaw&r=0");

        User saveUser = userService.saveUser(user);
        Message message = Message.builder().contant("Registration Successful").type(MessageType.green).build();
        session.setAttribute("message", message);
        System.out.print(saveUser);
        return "redirect:/register";
    }

}
