package com.gigasea.lms.service;

import com.gigasea.lms.model.ContactMessage;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    // Method to save the contact message
    public void saveContactMessage(ContactMessage contactMessage) {
        // Here, you can implement the logic to save the message, e.g., save to a database or send an email
        System.out.println("Message Received:");
        System.out.println("Name: " + contactMessage.getName());
        System.out.println("Email: " + contactMessage.getEmail());
        System.out.println("Message: " + contactMessage.getMessage());
    }
}