package com.fsdm.examsmanagement.service;

import com.fsdm.examsmanagement.dao.student.StudentDAO;
import com.fsdm.examsmanagement.model.Student;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Stateless
public class CallStudentService {
    @EJB
    private StudentDAO studentDAO;

    public void sendEmailWithPageManuelle(List<Long> idStudents){
        if (idStudents == null || idStudents.isEmpty()) {
            return;
        }

        final String from = "krimoaouad4@gmail.com";
        final String password = "wxaiucxdpttjggsm";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        List<Long> sentIds = new ArrayList<>();
        for (Long studentId : idStudents) {
            if (studentId == null || sentIds.contains(studentId)) {
                continue;
            }

            Student student = studentDAO.findById(studentId);
            if (student == null || student.getEmail() == null || student.getEmail().isBlank()) {
                continue;
            }

            String to = student.getEmail();
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                message.setSubject("Convocation à l'examen");
                message.setText("Bonjour " + student.getFirstName() + ",\n\nVous êtes convoqué(e) pour passer votre examen.\nMerci de consulter votre espace étudiant pour les détails.\n\nCordialement.");

                Transport.send(message);
                sentIds.add(studentId);
            } catch (MessagingException e) {
                throw new RuntimeException("Erreur lors de l'envoi de l'email à l'étudiant ID " + studentId, e);
            }
        }

    }
}
