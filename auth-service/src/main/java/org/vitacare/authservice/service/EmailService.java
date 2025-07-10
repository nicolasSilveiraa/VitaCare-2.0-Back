package org.vitacare.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String actualSenderEmail;

    public void sendPasswordResetEmail(String email, String token) {
        String resetUrl = "http://localhost:4200/reset-password?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("VitaCare <" + actualSenderEmail + ">");
        message.setTo(email);
        message.setSubject("Redefinição de Senha - Vitacare");
        message.setText("Olá,\n\nVocê solicitou a redefinição da sua senha. " +
                "Por favor, clique no link abaixo para criar uma nova senha:\n\n" +
                resetUrl +
                "\n\nSe você não solicitou isso, por favor, ignore este email." +
                "\n\nAtenciosamente,\nEquipe VitaCare");

        mailSender.send(message);
    }
}
