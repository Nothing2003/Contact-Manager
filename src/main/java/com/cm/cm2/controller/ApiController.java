package com.cm.cm2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cm.cm2.entities.Contact;
import com.cm.cm2.services.ContactService;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final ContactService contactService;

    public ApiController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contacts/{contactId}")
    public Contact getContact(@PathVariable String contactId) {
        return contactService.getContactById(contactId);
    }

    @GetMapping("/contacts/delete/{contactId}")
    public void getDelete(@PathVariable("contactId") String contactId) {
        contactService.deleteContact(contactId);
    }
}
