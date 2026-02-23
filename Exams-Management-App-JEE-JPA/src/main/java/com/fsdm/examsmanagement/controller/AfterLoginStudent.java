package com.fsdm.examsmanagement.controller;

import com.fsdm.examsmanagement.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/afterLoginStudent")
public class AfterLoginStudent extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);


        session.setAttribute("user",new User());

        PrintWriter out = resp.getWriter();
        if(session ==null){
            out.println("la session n'est ouvert.");
        }
        else{
            User user = (User) session.getAttribute("user");
            out.println("bonjour "+user.getFirstName() +" "+user.getLastName()+" ,c'est apres l'auhentification.");
        }
    }
}
