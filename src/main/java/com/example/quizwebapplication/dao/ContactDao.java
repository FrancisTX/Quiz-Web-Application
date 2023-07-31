package com.example.quizwebapplication.dao;

import com.example.quizwebapplication.domain.Contact;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactDao extends AbstractHibernateDao<Contact>{
    public ContactDao() {
        setClazz(Contact.class);
    }
    public void insertContact(Contact contact) {
        this.add(contact);
    }

    public List<Contact> getAllContacts() {
        return this.getAll();
    }
}
