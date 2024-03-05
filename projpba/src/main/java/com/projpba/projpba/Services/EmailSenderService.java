package com.projpba.projpba.Services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import java.io.UnsupportedEncodingException;

@Service
@Slf4j
@Async
public class EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void sendEmail(String email, String link) throws UnsupportedEncodingException, MessagingException {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom("rezerwacjaHotelu@gmail.com");
            helper.setTo(email);

            String subject = "Link do zresetowania hasła";

            String content = "<p>Czesc, </p>" +
                    "<p>Poprosiles o zmiane hasla.</p>" +
                    "<p>Kliknij w <a href=\"" + link + "\">link</a>, aby zmienic haslo</p>";
            helper.setSubject(subject);
            helper.setText(content, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            System.err.println("Wysyłanie zakończone błędem. Błąd: " + e.getMessage());
            throw e;
        }
    }
}
