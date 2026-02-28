package com.fsdm.examsmanagement.controller;

import com.fsdm.examsmanagement.dao.student.StudentDAO;
import com.fsdm.examsmanagement.model.Student;
import com.fsdm.examsmanagement.security.SessionGuard;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/convocationPageMauelleController")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,
    maxFileSize = 1024 * 1024 * 10,
    maxRequestSize = 1024 * 1024 * 50
)
public class ConvocationPageMauelleController extends HttpServlet {

    @EJB
    private StudentDAO studentDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!SessionGuard.requireRole(request, response, "admin")) {
            return;
        }
        int pageSize = 10;
        int currentPage = parsePositiveInt(request.getParameter("page"), 1);

        long totalStudents = studentDAO.countStudents();
        int totalPages = (int) Math.ceil((double) totalStudents / pageSize);
        if (totalPages == 0) {
            totalPages = 1;
        }
        if (currentPage > totalPages) {
            currentPage = totalPages;
        }

        List<Student> students = studentDAO.findPaginated(currentPage, pageSize);

        request.setAttribute("students", students);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalStudents", totalStudents);

        request.getRequestDispatcher("/WEB-INF/jsp/admin/ConvocationPageMauelle.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!SessionGuard.requireRole(request, response, "admin")) {
            return;
        }
        String title = request.getParameter("exam-title");
        String deadline = request.getParameter("exam-deadline");
        String duration = request.getParameter("exam-duration");

        if (title != null && deadline != null && duration != null) {
            Part filePart = request.getPart("exam-file");
            if (filePart != null && filePart.getSize() > 0) {
                try (InputStream inputStream = filePart.getInputStream()) {
                    byte[] fileBytes = inputStream.readAllBytes();
                    request.getSession(true).setAttribute("pendingExamFileBytes", fileBytes);
                    request.getSession().setAttribute("pendingExamFileName", filePart.getSubmittedFileName());
                }
            }
            request.getSession().setAttribute("pendingExamTitle", title);
            request.getSession().setAttribute("pendingExamDeadline", deadline);
            request.getSession().setAttribute("pendingExamDuration", duration);
            doGet(request, response);
            return;
        }

        String[] selectedStudentIds = request.getParameterValues("selectedStudentIds");
        if (selectedStudentIds == null) {
            request.setAttribute("selectedCount", 0);
        } else {
            List<Long> ids = Arrays.stream(selectedStudentIds)
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            request.setAttribute("selectedCount", ids.size());
        }
        doGet(request, response);
    }

    private int parsePositiveInt(String value, int defaultValue) {
        try {
            int parsed = Integer.parseInt(value);
            return parsed > 0 ? parsed : defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
