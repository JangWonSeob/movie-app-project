package com.website.movie.biz.service;

import javax.mail.MessagingException;

public interface EmailService {
    void sendMail(String toEmail, String subject, String message) throws MessagingException;
}
