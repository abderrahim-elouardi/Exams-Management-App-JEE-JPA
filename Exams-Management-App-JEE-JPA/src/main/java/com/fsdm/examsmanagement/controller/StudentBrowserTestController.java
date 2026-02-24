package com.fsdm.examsmanagement.controller;

import com.fsdm.examsmanagement.model.Student;
import com.fsdm.examsmanagement.repositories.StudentRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/student-test")
public class StudentBrowserTestController extends HttpServlet {

    private final StudentRepository studentRepository = new StudentRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        renderPage(resp, null, null);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("create".equals(action)) {
            handleCreate(req, resp);
            return;
        }

        if ("find".equals(action)) {
            handleFind(req, resp);
            return;
        }

        renderPage(resp, "Action invalide.", null);
    }

    private void handleCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = trim(req.getParameter("email"));
        String firstName = trim(req.getParameter("firstName"));
        String lastName = trim(req.getParameter("lastName"));
        String cne = trim(req.getParameter("cne"));
        String password = trim(req.getParameter("password"));

        if (email.isEmpty()) {
            renderPage(resp, "Email obligatoire pour créer un étudiant.", null);
            return;
        }

        Student existing = studentRepository.findStudentByEmail(email);
        if (existing != null) {
            renderPage(resp, "Étudiant déjà existant.", existing);
            return;
        }

        Student student = new Student();
        student.setEmail(email);
        student.setFirstName(firstName.isEmpty() ? "N/A" : firstName);
        student.setLastName(lastName.isEmpty() ? "N/A" : lastName);
        student.setCne(cne.isEmpty() ? "N/A" : cne);
        student.setPassword(password.isEmpty() ? "1234" : password);

        Student saved = studentRepository.saveStudent(student);
        renderPage(resp, "Étudiant créé avec succès.", saved);
    }

    private void handleFind(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = trim(req.getParameter("email"));
        if (email.isEmpty()) {
            renderPage(resp, "Email obligatoire pour rechercher.", null);
            return;
        }

        Student student = studentRepository.findStudentByEmail(email);
        if (student == null) {
            renderPage(resp, "Aucun étudiant trouvé pour cet email.", null);
            return;
        }

        renderPage(resp, "Étudiant trouvé.", student);
    }

    private void renderPage(HttpServletResponse resp, String message, Student student) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><meta charset='UTF-8'><title>Test Étudiant H2</title></head><body>");
        out.println("<h2>Test simple Étudiant (H2)</h2>");

        if (message != null) {
            out.println("<p><strong>" + escapeHtml(message) + "</strong></p>");
        }

        out.println("<h3>1) Créer un étudiant</h3>");
        out.println("<form method='post' action='student-test'>");
        out.println("<input type='hidden' name='action' value='create' />");
        out.println("Email: <input type='email' name='email' required /><br/><br/>");
        out.println("Prénom: <input type='text' name='firstName' /><br/><br/>");
        out.println("Nom: <input type='text' name='lastName' /><br/><br/>");
        out.println("CNE: <input type='text' name='cne' /><br/><br/>");
        out.println("Mot de passe: <input type='text' name='password' /><br/><br/>");
        out.println("<button type='submit'>Créer</button>");
        out.println("</form>");

        out.println("<h3>2) Rechercher un étudiant par email</h3>");
        out.println("<form method='post' action='student-test'>");
        out.println("<input type='hidden' name='action' value='find' />");
        out.println("Email: <input type='email' name='email' required />");
        out.println("<button type='submit'>Rechercher</button>");
        out.println("</form>");

        if (student != null) {
            out.println("<h3>Résultat</h3>");
            out.println("<p>ID: " + student.getId() + "</p>");
            out.println("<p>Email: " + escapeHtml(student.getEmail()) + "</p>");
        }

        out.println("</body></html>");
    }

    private String trim(String value) {
        return value == null ? "" : value.trim();
    }

    private String escapeHtml(String value) {
        return value
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;");
    }
}
