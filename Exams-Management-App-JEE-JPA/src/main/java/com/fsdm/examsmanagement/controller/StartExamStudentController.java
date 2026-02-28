package com.fsdm.examsmanagement.controller;

import com.fsdm.examsmanagement.dao.exam.ExamDAO;
import com.fsdm.examsmanagement.model.Exam;
import com.fsdm.examsmanagement.model.Questioner;
import com.fsdm.examsmanagement.model.Student;
import com.fsdm.examsmanagement.security.SessionGuard;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet("/startExamStudent")
public class StartExamStudentController extends HttpServlet {

    @EJB
    private ExamDAO examDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!SessionGuard.requireRole(req, resp, "student")) {
            return;
        }

        HttpSession session = req.getSession(false);
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            resp.sendRedirect(req.getContextPath() + "/authentification?role=student");
            return;
        }

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

        boolean assignedToStudent = student.getExamList() != null && student.getExamList().stream()
                .anyMatch(assignedExam -> assignedExam.getIdExam().equals(idExam));

        if (!assignedToStudent) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
            return;
        }

        boolean alreadyPassed = student.getPassedExamList() != null && student.getPassedExamList().stream()
                .anyMatch(passedExam -> passedExam.getIdExam().equals(idExam));

        if (alreadyPassed) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Exam already passed");
            return;
        }

        List<Questioner> shuffledQuestions = new ArrayList<>();
        if (exam.getQuestioner() != null) {
            shuffledQuestions.addAll(exam.getQuestioner());
            Collections.shuffle(shuffledQuestions);
        }

        int durationMinutes = (exam.getResponseTime() != null && exam.getResponseTime() > 0) ? exam.getResponseTime() : 30;

        req.setAttribute("exam", exam);
        req.setAttribute("questions", shuffledQuestions);
        req.setAttribute("durationMinutes", durationMinutes);
        req.getRequestDispatcher("/WEB-INF/jsp/StudentExamSession.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}