package com.fsdm.examsmanagement.controller;

import com.fsdm.examsmanagement.model.User;
import com.fsdm.examsmanagement.service.AuthentificationService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/authentification", "/jsp/authentification"})
public class AuthentificationController extends HttpServlet {

    @EJB
    private AuthentificationService authentificationService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String role = trim(request.getParameter("role"));
        request.setAttribute("selectedRole", role);
        request.getRequestDispatcher("/WEB-INF/jsp/authentification.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = trim(request.getParameter("email"));
        String password = trim(request.getParameter("password"));
        String role = trim(request.getParameter("role"));

        request.setAttribute("selectedRole", role);

        if (email.isEmpty() || password.isEmpty()) {
            request.setAttribute("loginSuccess", false);
            request.setAttribute("message", "Email et mot de passe sont obligatoires.");
            request.getRequestDispatcher("/WEB-INF/jsp/authentification.jsp").forward(request, response);
            return;
        }
        try {
            User user = authentificationService.authenticate(email, password, role);
            if (user != null) {
                request.getSession(true).setAttribute(role, user);
                request.setAttribute("loginSuccess", true);
                request.setAttribute("message", "Authentification réussie pour: " + user.getEmail());
            } else {
                request.setAttribute("loginSuccess", false);
                request.setAttribute("message", "Authentification échouée (email, mot de passe ou rôle invalide).");
            }
        } catch (Exception exception) {
            request.setAttribute("loginSuccess", false);
            request.setAttribute("message", "Erreur durant le test d'authentification: " + exception.getMessage());
        }
        if(role.equals("admin")){
            request.getRequestDispatcher("/WEB-INF/jsp/AfterLoginJspPageTeacher.jsp").forward(request, response);
        }
        if(role.equals("student")){
            request.getRequestDispatcher("/WEB-INF/jsp/AfterLoginJspPageStudent.jsp").forward(request, response);
        }
    }

    private String trim(String value) {
        return value == null ? "" : value.trim();
    }

    /*
    private String normalizeRole(String role) {
        String normalized = trim(role).toLowerCase();
        if ("professeur".equals(normalized)) {
            return "administrateur";
        }
        if (!"administrateur".equals(normalized) && !"etudiant".equals(normalized)) {
            return "etudiant";
        }
        return normalized;
    }
     */
}
