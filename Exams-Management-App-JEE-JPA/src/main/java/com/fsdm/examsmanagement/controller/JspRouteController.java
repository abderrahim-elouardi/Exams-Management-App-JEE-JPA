package com.fsdm.examsmanagement.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet({
        "/professeur-etudiant-choix-role.jsp",
        "/choixPreparationExam.jsp",
        "/preparerExam.jsp",
        "/preparerExamManuelle.jsp",
        "/ConvocationPage.jsp"
})
public class JspRouteController extends HttpServlet {

    private static final Map<String, String> JSP_ROUTES = Map.of(
            "/professeur-etudiant-choix-role.jsp", "/WEB-INF/jsp/professeur-etudiant-choix-role.jsp",
            "/choixPreparationExam.jsp", "/WEB-INF/jsp/admin/choixPreparationExam.jsp",
            "/preparerExam.jsp", "/WEB-INF/jsp/admin/preparerExam.jsp",
            "/preparerExamManuelle.jsp", "/WEB-INF/jsp/admin/preparerExamManuelle.jsp",
            "/ConvocationPage.jsp", "/WEB-INF/jsp/admin/ConvocationPage.jsp"
    );

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        String target = JSP_ROUTES.get(path);
        if (target == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        request.getRequestDispatcher(target).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}