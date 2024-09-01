package com.cm.cm2.services.imp;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cm.cm2.entities.Contact;
import com.cm.cm2.entities.User;
import com.cm.cm2.helper.ResourceNotFoundException;
import com.cm.cm2.repsitories.ContactRepo;
import com.cm.cm2.services.ContactService;

@Service
public class ContactServiceImp implements ContactService {

    @Autowired
    private ContactRepo contactRepo;

    @Override
    public Contact saveContact(Contact contact) {
        String contactId = UUID.randomUUID().toString();
        contact.setContactId(contactId);
        return contactRepo.save(contact);

    }

    @Override
    public Contact updateContact(Contact contact) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepo.findAll();
    }

    @Override
    public Contact getContactById(String id) {
        return contactRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact is not found."));
    }

    @Override
    public void deleteContact(String id) {
        contactRepo.deleteById(id);
    }

    @Override
    public List<Contact> searchByName(String name, String email, String phoneNumber) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Contact> getByUserId(String id) {

        return (List<Contact>) contactRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact is not found."));
    }

    @Override
    public Page<Contact> getByUser(User user, int page, int size, String sortBy, String direction) {

        return contactRepo.findByUser(user, PageRequest.of(page, size, direction.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending()));
    }
}
