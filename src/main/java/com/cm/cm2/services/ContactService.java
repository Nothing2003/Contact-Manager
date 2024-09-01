package com.cm.cm2.services;

import java.util.List;

import org.springframework.data.domain.Page;
import com.cm.cm2.entities.Contact;
import com.cm.cm2.entities.User;

public interface ContactService {

    Contact saveContact(Contact contact);

    Contact updateContact(Contact contact);

    List<Contact> getAllContacts();

    Contact getContactById(String id);

    void deleteContact(String id);

    List<Contact> searchByName(String name, String email, String phoneNumber);

    List<Contact> getByUserId(String id);

    Page<Contact> getByUser(User user,int page, int size,  String sortBy, String direction);

}
