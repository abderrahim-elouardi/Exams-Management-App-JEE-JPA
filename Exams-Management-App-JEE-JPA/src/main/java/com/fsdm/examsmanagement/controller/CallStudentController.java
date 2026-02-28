package com.fsdm.examsmanagement.controller;

import com.fsdm.examsmanagement.dao.student.StudentDAO;
import com.fsdm.examsmanagement.model.Student;
import jakarta.ejb.EJB;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Properties;

import jakarta.mail.*;
import jakarta.mail.internet.*;


@WebServlet("/callStudent")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 10,  // 10MB
        maxRequestSize = 1024 * 1024 * 50
)
public class CallStudentController extends HttpServlet {

    @EJB
    StudentDAO studentDAO;


    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        Part filePart = request.getPart("student-file");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(filePart.getInputStream())
        );
        reader.lines().forEach((l)->{
            String studentId = l.trim();
            Student student = studentDAO.findById((long) Integer.parseInt(studentId));
            if(student!=null){
                String studentEmail = student.getEmail();
                final String from = "tonemail@gmail.com";
                final String password = "MOT_DE_PASSE_APPLICATION";
                String to = "destinataire@gmail.com";

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

                try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(from));
                    message.setRecipients(
                            Message.RecipientType.TO,
                            InternetAddress.parse(to)
                    );
                    message.setSubject("Test email Java");
                    message.setText("Bonjour,\n\nEmail envoyé depuis une application Java.");

                    Transport.send(message);

                    System.out.println("Email envoyé avec succès ✅");

                    request.getRequestDispatcher("/afterLoginTeacher").forward(request, response);

                } catch (MessagingException e) {
                    e.printStackTrace();
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
