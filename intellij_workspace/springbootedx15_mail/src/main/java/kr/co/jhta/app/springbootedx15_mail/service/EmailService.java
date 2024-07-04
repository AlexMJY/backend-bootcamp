package kr.co.jhta.app.springbootedx15_mail.service;

import kr.co.jhta.app.springbootedx15_mail.entity.EmailMessage;

public interface EmailService {
    public String sendMail(EmailMessage emailMessage);
}
