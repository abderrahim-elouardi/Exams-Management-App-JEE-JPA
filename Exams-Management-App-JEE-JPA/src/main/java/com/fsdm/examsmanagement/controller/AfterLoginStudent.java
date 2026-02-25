package com.fsdm.examsmanagement.controller;

import com.fsdm.examsmanagement.dao.student.StudentDAO;
import com.fsdm.examsmanagement.model.Student;
import jakarta.ejb.EJB;
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

    @EJB
    StudentDAO studentDAO;

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
            Student student = studentDAO.findByEmailAndPassword("username", "password");
            session.setAttribute("student",student);
            session.setAttribute("exams",student.getExamList());
            req.getRequestDispatcher("/WEB-INF/jsp/AfterLoginJspPageStudent.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException,IOException{
        doPost(req, resp);
    }
}
