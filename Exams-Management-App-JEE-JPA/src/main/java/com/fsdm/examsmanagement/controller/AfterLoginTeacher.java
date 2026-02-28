package com.fsdm.examsmanagement.controller;

import com.fsdm.examsmanagement.dao.administrator.AdministratorDAO;
import com.fsdm.examsmanagement.model.Administrator;
import com.fsdm.examsmanagement.security.SessionGuard;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/afterLoginTeacher")
public class AfterLoginTeacher extends HttpServlet {

    @EJB
    AdministratorDAO administratorDAO;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!SessionGuard.requireRole(req, resp, "admin")) {
            return;
        }
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession(false);
        if(session ==null){
            req.getRequestDispatcher("/WEB-INF/jsp/AuthenticationError.jsp").forward(req, resp);
            return;
        }
        Administrator admin = (Administrator) session.getAttribute("admin");
        if (admin == null) {
            req.getRequestDispatcher("/WEB-INF/jsp/AuthenticationError.jsp").forward(req, resp);
            return;
        }
        session.setAttribute("exams",admin.getExamList());
        System.out.println(admin.getExamList().toArray().length);
        req.getRequestDispatcher("/WEB-INF/jsp/AfterLoginJspPageTeacher.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException,IOException{
        doPost(req, resp);
    }
}
