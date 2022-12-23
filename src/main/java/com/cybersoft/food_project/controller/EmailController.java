package com.cybersoft.food_project.controller;

import com.cybersoft.food_project.model.EmailDetail;
import com.cybersoft.food_project.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    // Sending a simple Email
    @PostMapping("/sendMail")
    public String
    sendMail(@RequestBody EmailDetail detail)
    {
        String status
                = emailService.sendSimpleMail(detail);

        return status;
    }

    // Sending email with attachment
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(
            @RequestBody EmailDetail detail)
    {
        String status
                = emailService.sendMailWithAttachment(detail);

        return status;
    }
}
