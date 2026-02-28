package com.fsdm.examsmanagement.controller;

import com.fsdm.examsmanagement.dao.exam.ExamDAO;
import com.fsdm.examsmanagement.model.Administrator;
import com.fsdm.examsmanagement.model.Exam;
import com.fsdm.examsmanagement.security.SessionGuard;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/examDetails")
public class ExamDetailsController extends HttpServlet {

    @EJB
    private ExamDAO examDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!SessionGuard.requireRole(req, resp, "admin")) {
            return;
        }
        HttpSession session = req.getSession(false);

        String idExamParam = req.getParameter("idExam");
        if (idExamParam == null || idExamParam.isBlank()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "idExam is required");
            return;
        }

        Long idExam;
        try {
            idExam = Long.parseLong(idExamParam);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "idExam is invalid");
            return;
        }

        Exam exam = examDAO.findExamWithDetails(idExam);
        if (exam == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Exam not found");
            return;
        }

        Administrator admin = (Administrator) session.getAttribute("admin");
        if (exam.getAdmin() == null || !exam.getAdmin().getId().equals(admin.getId())) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
            return;
        }

        req.setAttribute("exam", exam);
        req.getRequestDispatcher("/WEB-INF/jsp/admin/ExamDetailsTeacher.jsp").forward(req, resp);
    }
}
