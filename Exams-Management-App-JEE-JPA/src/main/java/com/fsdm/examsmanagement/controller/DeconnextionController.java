package com.fsdm.examsmanagement.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/deconnection")
public class DeconnextionController extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // On récupère sans créer
        if (session != null) {
            session.invalidate(); // On détruit si elle existe
        }
        // Ensuite, on redirige vers la page de login
        response.sendRedirect(request.getContextPath()+"/professeur-etudiant-choix-role.jsp");
    }

}
