package com.fsdm.examsmanagement.controller;

import com.fsdm.examsmanagement.dao.administrator.AdministratorDAO;
import com.fsdm.examsmanagement.dao.exam.ExamDAO;
import com.fsdm.examsmanagement.dao.student.StudentDAO;
import com.fsdm.examsmanagement.model.Administrator;
import com.fsdm.examsmanagement.model.Exam;
import com.fsdm.examsmanagement.model.Student;
import com.fsdm.examsmanagement.security.SessionGuard;
import jakarta.ejb.EJB;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.http.Part;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

@WebServlet("/callStudent")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50
)
public class CallStudentController extends HttpServlet {

    @EJB
    StudentDAO studentDAO;

    @EJB
    ExamDAO examDAO;

    @EJB
    AdministratorDAO administratorDAO;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, IOException, ServletException {
        if (!SessionGuard.requireRole(request, response, "admin")) return;

        Part filePart = request.getPart("student-file");
        // Récupération de l'examen depuis la session et rafraîchissement via DAO
        Exam examSession = (Exam) request.getSession().getAttribute("exam");
        if (examSession == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Examen non trouvé en session");
            return;
        }

        // On récupère une instance "vivante" de l'examen
        Exam exam = examDAO.findById(examSession.getIdExam());

        // Configuration du mail (On le fait une seule fois hors de la boucle pour la session)
        final String from = "krimoaouad4@gmail.com";
        final String password = "wxaiucxdpttjggsm";
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session mailSession = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(filePart.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String studentIdStr = line.trim();
                if (studentIdStr.isEmpty()) continue;

                Student student = studentDAO.findById(Long.parseLong(studentIdStr));

                if (student != null) {

                    if (!student.getExamList().contains(exam)) {
                        student.getExamList().add(exam);
                    }

                    // On ajoute l'étudiant à l'examen (Côté Esclave - pour la cohérence en mémoire)
                    if (!exam.getStudentList().contains(student)) {
                        exam.getStudentList().add(student);
                    }
                    studentDAO.save(student);

                    // 2. Envoi du mail avec validation d'adresse
                    String to = student.getEmail();
                    if (to != null && to.contains("@")) { // Validation basique pour éviter 553 RFC 5321
                        try {
                            Message message = new MimeMessage(mailSession);
                            message.setFrom(new InternetAddress(from));
                            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                            message.setSubject("Convocation à l'examen : " + exam.getTitre());
                            message.setText("Bonjour " + student.getLastName() + " " +student.getFirstName() + ",\n\nVous êtes convoqué pour l'examen.");

                            Transport.send(message);
                            System.out.println("Email envoyé à : " + to);
                        } catch (MessagingException e) {
                            System.err.println("Erreur SMTP pour " + to + " : " + e.getMessage());
                        }
                    } else {
                        System.err.println("Format d'email invalide sauté : " + to);
                    }
                }
            }

            // 3. SAUVEGARDE de l'examen (C'est ici que le Lazy Loading est résolu)
            // merge va persister les liens dans la table de jointure
            examDAO.save(exam);

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/afterLoginTeacher").forward(request,response);
    }
}