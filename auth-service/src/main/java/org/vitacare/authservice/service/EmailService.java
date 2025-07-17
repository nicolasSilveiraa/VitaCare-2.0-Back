package org.vitacare.authservice.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String actualSenderEmail;

    public void sendPasswordResetEmail(String toEmail, String token) {
        String resetUrl = "http://localhost:4200/reset-password?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(actualSenderEmail);
        message.setTo(toEmail);
        message.setSubject("Redefinição de Senha - Vitacare");
        message.setText("Olá,\n\nVocê solicitou a redefinição da sua senha. " +
                "Por favor, clique no link abaixo para criar uma nova senha:\n\n" +
                resetUrl +
                "\n\nSe você não solicitou isso, por favor, ignore este email." +
                "\n\nAtenciosamente,\nEquipe VitaCare");

        mailSender.send(message);
        logger.info("Email de redefinição de senha enviado para {}", toEmail);
    }
}
