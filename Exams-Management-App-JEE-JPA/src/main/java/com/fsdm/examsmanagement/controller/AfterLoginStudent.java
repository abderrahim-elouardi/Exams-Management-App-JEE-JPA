package com.fsdm.examsmanagement.controller;

import com.fsdm.examsmanagement.dao.User.UserDAO;
import com.fsdm.examsmanagement.dao.administrator.AdministratorDAO;
import com.fsdm.examsmanagement.dao.exam.ExamDAOImp;
import com.fsdm.examsmanagement.dao.student.StudentDAO;
import com.fsdm.examsmanagement.model.Administrator;
import com.fsdm.examsmanagement.model.Exam;
import com.fsdm.examsmanagement.model.Student;
import com.fsdm.examsmanagement.model.User;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
//import jakarta.inject.Inject;

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
            Student student = studentDAO.findByEmailAndPassword("username","password");
            session.setAttribute("student",student);
            session.setAttribute("exams",student.getExamList());
            req.getRequestDispatcher("/WEB-INF/jsp/AfterLoginJspPage.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException,IOException{
        doPost(req, resp);
    }
}
