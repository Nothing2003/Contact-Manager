package com.cm.cm2.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cm.cm2.entities.Contact;
import com.cm.cm2.forms.ContactForm;
import com.cm.cm2.helper.AppCon;
import com.cm.cm2.helper.Helper;
import com.cm.cm2.helper.Message;
import com.cm.cm2.helper.MessageType;
import com.cm.cm2.services.ContactService;
import com.cm.cm2.services.ImageService;
import com.cm.cm2.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    private final ContactService contactService;
    private final UserService userService;
    private final ImageService imageService;

    public ContactController(ContactService contactService, UserService userService, ImageService imageService) {
        this.contactService = contactService;
        this.userService = userService;
        this.imageService = imageService;
    }

    @GetMapping("/add_contact")
    public String addContact(Model model) {
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);

        return "user/add_contact";
    }

    @PostMapping("/add_contact")
    public String save(@Valid @ModelAttribute ContactForm contactForm, BindingResult bindingResult, Authentication authentication, HttpSession session) {
        if (bindingResult.hasErrors()) {
            session.setAttribute("message", Message.builder()
                    .contant("Please correct the following errors. ")
                    .type(MessageType.red)
                    .build());
            return "user/add_contact";
        }
        String username = Helper.getEmailOfLoginUser(authentication);
        String fileURL = imageService.uploadImage(contactForm.getContactImage());

        Contact contact = new Contact();
        BeanUtils.copyProperties(contactForm, contact);
        contact.setUser(userService.getUserByEmail(username));
        contact.setPic(fileURL);

        contactService.saveContact(contact);
        System.out.println(contactForm);

        session.setAttribute("message", Message.builder()
                .contant("You has been successfully added a new contact. ")
                .type(MessageType.green)
                .build());
        return "redirect:/user/contacts/add_contact";
    }

    @RequestMapping
    public String viewContact(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            Model model, Authentication authentication) {
        Page<Contact> PageContact = contactService.getByUser(userService.getUserByEmail(Helper.getEmailOfLoginUser(authentication)), page, size, sortBy, direction);
        model.addAttribute("contacts", PageContact);
        model.addAttribute("pagesize", AppCon.Page_Size);
        return "user/contacts";
    }

}
