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

    Page<Contact> searchByName(String name, int page, int size, String sortBy, String direction, User user);

    Page<Contact> searchByEmail(String email, int page, int size, String sortBy, String direction, User user);

    Page<Contact> searchByPhoneNumber(String phoneNumber, int page, int size, String sortBy, String direction, User user);

    List<Contact> getByUserInList(User user);

    Page<Contact> getByUser(User user, int page, int size, String sortBy, String direction);

}
