package com.cm.cm2.controller;

import com.cm.cm2.forms.UpdateUserFrom;
import com.cm.cm2.forms.UserForm;
import com.cm.cm2.helper.Helper;
import com.cm.cm2.services.ImageService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.cm.cm2.services.UserService;
import com.cm.cm2.entities.User;
import com.cm.cm2.helper.Message;
import com.cm.cm2.helper.MessageType;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userServiceimp;
private final ImageService imageService;
    public UserController(UserService userServiceimp,ImageService imageService) {
        this.userServiceimp = userServiceimp;
        this.imageService=imageService;
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
    @GetMapping(value = "/update")
    public String updateUser( Authentication authentication,Model model,HttpSession session){
       if (authentication==null){
           session.setAttribute("message", Message.builder().contant("User Not found").type(MessageType.red).build());
           return "error_page";
       }
        String userName = Helper.getEmailOfLoginUser(authentication);
       User user =userServiceimp.getUserByEmail(userName);
       UpdateUserFrom updateUserFrom=new UpdateUserFrom();
       updateUserFrom.setName(user.getName());
       updateUserFrom.setEmail(user.getEmail());
       updateUserFrom.setAbout(user.getAbout());
       updateUserFrom.setPhoneNo(user.getPhoneNo());
       updateUserFrom.setPicture(user.getProfilePic());
       model.addAttribute("updateUserForm",updateUserFrom);
        return "user/update_user";
    }
    @PostMapping(value = "/doUpdate")
    public String updatedUser(@Valid @ModelAttribute UpdateUserFrom updateUserForm, BindingResult bindingResult, HttpSession session){
        if (bindingResult.hasErrors()) {
            return "user/update_user";
        }
        User user1 = userServiceimp.getUserByEmail(updateUserForm.getEmail());
        if (user1 == null) {
            session.setAttribute("message", Message.builder().contant("This Email Id is already present").type(MessageType.red).build());
            return "user/profile";
        }
        user1.setName(updateUserForm.getName());
        user1.setAbout(updateUserForm.getAbout());
        user1.setPhoneNo(updateUserForm.getPhoneNo());
        if (updateUserForm.getPrifileImage().isEmpty() && updateUserForm.getPrifileImage() ==null){
            user1.setProfilePic(updateUserForm.getPicture());
        }else{
            String image=imageService.uploadImage(updateUserForm.getPrifileImage());
            user1.setProfilePic(image);
        }
        userServiceimp.updateUser(user1);
        Message message = Message.builder().contant("Update Successful! ").type(MessageType.green).build();
        session.setAttribute("message", message);
        return "user/profile";
    }
}
