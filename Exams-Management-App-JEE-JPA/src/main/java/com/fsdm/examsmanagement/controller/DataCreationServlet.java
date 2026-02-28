package com.fsdm.examsmanagement.controller;

import com.fsdm.examsmanagement.dao.administrator.AdministratorDAO;
import com.fsdm.examsmanagement.dao.exam.ExamDAO;
import com.fsdm.examsmanagement.dao.student.StudentDAO;
import com.fsdm.examsmanagement.model.Administrator;
import com.fsdm.examsmanagement.model.Exam;
import com.fsdm.examsmanagement.model.Student;
import jakarta.ejb.EJB;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

@WebServlet("/createData")
public class DataCreationServlet extends HttpServlet {

    @EJB
    ExamDAO examDAO;
    @EJB
    AdministratorDAO administratorDAO;
    @EJB
    StudentDAO studentDAO;
    @Override
    public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException {

        // 1. Créer et SAUVEGARDER l'administrateur en premier
        Administrator admin = new Administrator();
        admin.setEmail("username@gmail.com");
        admin.setPassword("password");
        admin.setLastName("kamal");
        admin.setFirstName("ismaili");
        admin.setCin("cdd44444");

        // On persiste l'admin pour qu'il reçoive un ID de la base
        administratorDAO.save(admin);


        System.out.println("----******************_____");
        for(Administrator n:administratorDAO.findAll()){
            System.out.println(n.getEmail());
        }
        // 2. Créer les examens en utilisant l'admin déjà sauvegardé
        Exam exam1 = new Exam();
        exam1.setTitre("JEE Exam 1");
        exam1.setDeadline(LocalDate.now().plusDays(5));
        exam1.setAdmin(admin);

        Exam exam2 = new Exam();
        exam2.setTitre("JEE Exam 2");
        exam2.setDeadline(LocalDate.now().plusDays(5));
        exam2.setAdmin(admin);

        // 3. Sauvegarder les examens
        examDAO.save(exam1);
        examDAO.save(exam2);

        // Optionnel : Mettre à jour la liste côté Java (pour la cohérence de l'objet en mémoire)
        admin.setExamList(List.of(exam1, exam2));


        System.out.println("--- l'enregeistrement bien effectue ------------");
        for(Exam exam : examDAO.findAll()){
            System.out.println(exam);
        }

        Student student = new Student();
        student.setCne("n13245567");
        student.setEmail("username@gmai.com");
        student.setPassword("password");
        student.setFirstName("abderrahim");
        student.setLastName("el ouardi");


        student.setExamList(List.of(exam1));
        student.setPassedExamList(List.of(exam2));
        studentDAO.save(student);
        System.out.println("student was saved");


        System.out.println("------------*****************************************");
        for(int i =0;i<10;i++){
            System.out.println("iteration d'insertion des etduaint "+i);
            Student student1 = new Student();
            student1.setLastName("LastName"+i);
            student1.setFirstName("FirstName"+i);
            student1.setCne("Cne"+i);
            student1.setPassword("password"+i);
            student1.setEmail("email@"+i+"gmail.com");
            studentDAO.save(student1);
            System.out.println("student "+i+"was saved"+student1.getId());
        }

        System.out.println("------------***************************************************************************");
        for(long i =9;i<13;i++){
            System.out.println(studentDAO.findById(i).toString());
        }


        System.out.println("sanding name--------------");
        final String from = "abderrahim.elouardi1@usmba.ac.ma";
        final String password = "";
        String to = "elouardiabderrahim06@gmail.com";

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

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        System.out.println("mail sanded");


    }


}
