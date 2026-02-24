package com.fsdm.examsmanagement.controller;

import com.fsdm.examsmanagement.model.User;
import com.fsdm.examsmanagement.service.AuthentificationService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class AuthentificationController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        /*String role = request.getParameter("role");
        PrintWriter out = response.getWriter();
        AuthentificationService authentificationService = new AuthentificationService(
          request.getParameter("email"), request.getParameter("password"), role
        );
        User user = authentificationService.authenticate();
        request.getSession(true);
        request.setAttribute("user", user);
        switch (role){
            case "administrateur":
                response.sendRedirect("");
            case "etudiant" :
                out.println(user.getId());
                out.println(user.getEmail());
        }*/
    }
    public void dePost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doGet(request,response);
    }
}
