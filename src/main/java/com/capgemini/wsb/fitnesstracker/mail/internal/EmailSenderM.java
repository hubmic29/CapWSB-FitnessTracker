package com.capgemini.wsb.fitnesstracker.mail.internal;
import com.capgemini.wsb.fitnesstracker.mail.api.EmailDto;
import com.capgemini.wsb.fitnesstracker.mail.api.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
/**
 * Implementation of {@link EmailSender} interface.
 */
@RequiredArgsConstructor
@Service
public class EmailSenderM implements EmailSender {

    @Override
    public void send(EmailDto email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.toAddress());
        message.setSubject(email.subject());
        message.setText(email.content());
    }
}