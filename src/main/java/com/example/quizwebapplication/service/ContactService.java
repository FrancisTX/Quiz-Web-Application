package com.example.quizwebapplication.service;

import com.example.quizwebapplication.dao.ContactDao;
import com.example.quizwebapplication.domain.Contact;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ContactService {
    ContactDao contactDao;

    public ContactService(ContactDao contactDao) {
        this.contactDao = contactDao;
    }


    @Transactional
    public void insertContact(Contact contact) {
        contactDao.insertContact(contact);
    }

    @Transactional
    public List<Contact> getAllContacts() {
        return contactDao.getAllContacts();
    }
}
