package com.cybersoft.food_project.service;

import com.cybersoft.food_project.model.EmailDetail;

public interface EmailService {
    // To send a simple email
    String sendSimpleMail(EmailDetail detail);

    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDetail detail);
}
