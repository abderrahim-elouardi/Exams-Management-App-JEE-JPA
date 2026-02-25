package com.fsdm.examsmanagement.controller;

import com.fsdm.examsmanagement.dao.Administrator.AdministratorDAO;
import com.fsdm.examsmanagement.dao.Student.StudentDAO;
import com.fsdm.examsmanagement.model.Administrator;
import com.fsdm.examsmanagement.model.Student;
import com.fsdm.examsmanagement.security.PasswordSecurity;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
//import jakarta.inject.Inject;

@WebServlet("/afterLoginTeacher")
public class AfterLoginTeacher extends HttpServlet {

    @EJB
    AdministratorDAO administratorDAO;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession(true);
        PrintWriter out = resp.getWriter();
        if(session ==null){
            req.getRequestDispatcher("/WEB-INF/jsp/AuthenticationError.jsp").forward(req, resp);
        }
        else{
            //recuperation de la liste des exams
            Administrator admin = administratorDAO.findByEmailAndPassword("username@gmail.com", "password");
            session.setAttribute("admin",admin);
            session.setAttribute("exams",admin.getExamList());
            req.getRequestDispatcher("/WEB-INF/jsp/AfterLoginJspPageTeacher.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException,IOException{
        doPost(req, resp);
    }
}
