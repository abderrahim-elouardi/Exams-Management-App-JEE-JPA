package com.fsdm.examsmanagement.controller;

import com.fsdm.examsmanagement.dao.student.StudentDAO;
import com.fsdm.examsmanagement.model.Student;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/convocationPageMauelleController")
public class ConvocationPageMauelleController extends HttpServlet {

    @EJB
    private StudentDAO studentDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        request.getRequestDispatcher("/ConvocationPageMauelle.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
