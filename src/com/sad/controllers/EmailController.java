package com.sad.controllers;

import com.sad.utils.Security;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailController {


    private static String SEND;


    public static String getSEND() {
        return SEND;
    }


    public static void generateAndSendEmail(String args) throws IOException {

        SEND = String.valueOf( Security.OTP( 6 ) );

        final String username = "Yetitoseeyou@outlook.com";
        final String password = "Yetimail@java";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "outlook.office365.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("Yetitoseeyou@outlook.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("swapnilp618@gmail.com"));
            message.setSubject("Your One Time Password");
            message.setText("Secure Email by Swapnil. " + "\nYour one time password is: " + SEND + "\nRegards, \nSwapnil");
            Transport.send(message);
            System.out.println("Mail Session has been created successfully..");


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}