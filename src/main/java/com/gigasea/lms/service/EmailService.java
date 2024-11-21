package com.gigasea.lms.util;

import com.gigasea.lms.model.ContactMessage;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailService {

    private final String username = "hjayanth914@gmail.com"; // Replace with your email
    private final String password = "tbgewskvavicrweo"; // Replace with your email password
    private final String smtpHost = "smtp.gmail.com"; // Replace with your SMTP server
    private final String smtpPort = "587"; // Replace with your SMTP port

    public void sendEmail(ContactMessage contactMessage) throws MessagingException {
        // Set up the SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);

        // Create a session with an authenticator
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // Create a message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("hjayanth914@gmail.com")); // Replace with the recipient email address
        message.setSubject("New Contact Message from " + contactMessage.getName());
        message.setText("You have received a new contact message:\n\n" +
                "Name: " + contactMessage.getName() + "\n" +
                "Email: " + contactMessage.getEmail() + "\n" +
                "Message: " + contactMessage.getMessage());

        // Send the message
        Transport.send(message);
        System.out.println("Email sent successfully!");
    }
}