package com.fsdm.examsmanagement.controller;

import com.fsdm.examsmanagement.dao.examnote.ExamNoteDAO;
import com.fsdm.examsmanagement.dao.student.StudentDAO;
import com.fsdm.examsmanagement.model.ExamNote;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet("/afterLoginStudent")
public class AfterLoginStudent extends HttpServlet {

    @EJB
    StudentDAO studentDAO;

    @EJB
    private ExamNoteDAO examNoteDAO;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!SessionGuard.requireRole(req, resp, "student")) {
            return;
        }
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession(true);
        if(session ==null){
            req.getRequestDispatcher("/WEB-INF/jsp/authentification/AuthenticationError.jsp").forward(req, resp);
        }
        else{
            Student student = (Student) session.getAttribute("student");
            session.setAttribute("exams",student.getExamList());
            session.setAttribute("passed_exams",student.getPassedExamList());

            Map<Long, Float> passedExamNotes = new HashMap<>();
            List<ExamNote> examNotes = examNoteDAO.findPassedByStudentId(student.getId());
            for (ExamNote examNote : examNotes) {
                if (examNote.getExam() != null && examNote.getExam().getIdExam() != null) {
                    passedExamNotes.put(examNote.getExam().getIdExam(), examNote.getNote());
                }
            }
            session.setAttribute("passed_exam_notes", passedExamNotes);

            req.getRequestDispatcher("/WEB-INF/jsp/student/AfterLoginJspPageStudent.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException,IOException{
        doPost(req, resp);
    }
}
