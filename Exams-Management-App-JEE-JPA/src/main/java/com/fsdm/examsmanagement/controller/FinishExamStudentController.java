package com.fsdm.examsmanagement.controller;

import com.fsdm.examsmanagement.dao.exam.ExamDAO;
import com.fsdm.examsmanagement.dao.examnote.ExamNoteDAO;
import com.fsdm.examsmanagement.model.Exam;
import com.fsdm.examsmanagement.model.QCM;
import com.fsdm.examsmanagement.model.QCMAnswer;
import com.fsdm.examsmanagement.model.QFillInBlank;
import com.fsdm.examsmanagement.model.QFillInBlankAnswer;
import com.fsdm.examsmanagement.model.QShort;
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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/finishExamStudent")
public class FinishExamStudentController extends HttpServlet {

    private static final int DEFAULT_QUESTION_POINTS = 2;

    @EJB
    private ExamNoteDAO examNoteDAO;

    @EJB
    private ExamDAO examDAO;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        boolean assignedToStudent = student.getExamList() != null && student.getExamList().stream()
                .anyMatch(assignedExam -> assignedExam.getIdExam().equals(idExam));

        if (!assignedToStudent) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
            return;
        }

        Exam exam = examDAO.findExamWithDetails(idExam);
        if (exam == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Exam not found");
            return;
        }

        float note = calculateScore(req, exam.getQuestioner());
        examNoteDAO.saveExamResult(student.getId(), idExam, note, true);

        if (exam != null) {
            if (student.getPassedExamList() != null && student.getPassedExamList().stream().noneMatch(e -> e.getIdExam().equals(idExam))) {
                student.getPassedExamList().add(exam);
            }
            if (student.getExamList() != null) {
                student.getExamList().removeIf(e -> e.getIdExam().equals(idExam));
            }
        }

        resp.sendRedirect(req.getContextPath() + "/afterLoginStudent");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    private float calculateScore(HttpServletRequest req, List<Questioner> questions) {
        if (questions == null || questions.isEmpty()) {
            return 0f;
        }

        float total = 0f;
        for (Questioner question : questions) {
            if (question != null && isCorrectAnswer(req, question)) {
                total += resolveQuestionPoints(question);
            }
        }
        return total;
    }

    private int resolveQuestionPoints(Questioner question) {
        return question.getPoint() > 0 ? question.getPoint() : DEFAULT_QUESTION_POINTS;
    }

    private boolean isCorrectAnswer(HttpServletRequest req, Questioner question) {
        if (question instanceof QCM qcm) {
            return isQcmCorrect(req, qcm);
        }
        if (question instanceof QFillInBlank qFillInBlank) {
            return isFillInBlankCorrect(req, qFillInBlank);
        }
        if (question instanceof QShort qShort) {
            return isShortAnswerCorrect(req, qShort);
        }
        return false;
    }

    private boolean isQcmCorrect(HttpServletRequest req, QCM qcm) {
        if (qcm.getId() == null || qcm.getAnswerList() == null || qcm.getAnswerList().isEmpty()) {
            return false;
        }

        String[] selectedValues = req.getParameterValues("q_" + qcm.getId());
        Set<Long> selectedAnswerIds = parseIds(selectedValues);

        Set<Long> correctAnswerIds = qcm.getAnswerList().stream()
                .filter(Objects::nonNull)
                .filter(answer -> answer.getStatus() == 1)
                .map(QCMAnswer::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        if (correctAnswerIds.isEmpty()) {
            return false;
        }

        return selectedAnswerIds.equals(correctAnswerIds);
    }

    private boolean isFillInBlankCorrect(HttpServletRequest req, QFillInBlank question) {
        if (question.getId() == null || question.getQFillInBlankAnswer() == null || question.getQFillInBlankAnswer().isEmpty()) {
            return false;
        }

        List<String> expectedAnswers = question.getQFillInBlankAnswer().stream()
                .filter(Objects::nonNull)
                .map(QFillInBlankAnswer::getAnswer)
                .filter(Objects::nonNull)
                .map(String::trim)
                .collect(Collectors.toList());

        if (expectedAnswers.isEmpty()) {
            return false;
        }

        for (int index = 0; index < expectedAnswers.size(); index++) {
            String submittedAnswer = req.getParameter("q_" + question.getId() + "_blank_" + index);
            if (submittedAnswer == null || !submittedAnswer.trim().equalsIgnoreCase(expectedAnswers.get(index))) {
                return false;
            }
        }

        return true;
    }

    private boolean isShortAnswerCorrect(HttpServletRequest req, QShort qShort) {
        if (qShort.getId() == null) {
            return false;
        }

        if (qShort.getAnswer() != null && qShort.getAnswer().getId() != null) {
            String[] selectedValues = req.getParameterValues("q_" + qShort.getId());
            Set<Long> selectedAnswerIds = parseIds(selectedValues);
            return selectedAnswerIds.contains(qShort.getAnswer().getId());
        }

        String submittedText = req.getParameter("q_" + qShort.getId() + "_short");
        String expectedText = qShort.getAnswer() != null ? qShort.getAnswer().getAnswer() : null;
        return submittedText != null
                && expectedText != null
                && submittedText.trim().equalsIgnoreCase(expectedText.trim());
    }

    private Set<Long> parseIds(String[] values) {
        if (values == null || values.length == 0) {
            return new HashSet<>();
        }

        return Arrays.stream(values)
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(value -> !value.isEmpty())
                .map(this::parseLongSafe)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    private Long parseLongSafe(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
