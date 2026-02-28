package com.fsdm.examsmanagement.controller;

import com.fsdm.examsmanagement.model.Administrator;
import com.fsdm.examsmanagement.security.SessionGuard;
import com.fsdm.examsmanagement.service.PreparedExamService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Contrôleur qui gère la création d'un examen.
 * Il récupère les données du formulaire et appelle le service métier.
 */
@WebServlet("/preparerExamController")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 10,  // 10MB
        maxRequestSize = 1024 * 1024 * 50
)
public class PreparerExamController extends HttpServlet {
    @EJB
    private PreparedExamService preparedExamService;

    /**
     * Traite l'envoi du formulaire de préparation d'examen.
     * Les données manquantes peuvent être récupérées depuis la session.
     *
     * @param request requête HTTP contenant les champs du formulaire
     * @param response réponse HTTP pour redirection ou erreur
     */
    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {
        if (!SessionGuard.requireRole(request, response, "admin")) {
            return;
        }
        String title = request.getParameter("exam-title");
        String deadlineValue = request.getParameter("exam-deadline");
        String durationValue = request.getParameter("exam-duration");

        Part filePart = null;
        String contentType = request.getContentType();
        boolean isMultipartRequest = contentType != null && contentType.toLowerCase().startsWith("multipart/");
        if (isMultipartRequest) {
            filePart = request.getPart("exam-file");
        }

        if (title == null || title.isBlank()) {
            title = (String) request.getSession().getAttribute("pendingExamTitle");
        }
        if (deadlineValue == null || deadlineValue.isBlank()) {
            deadlineValue = (String) request.getSession().getAttribute("pendingExamDeadline");
        }
        if (durationValue == null || durationValue.isBlank()) {
            durationValue = (String) request.getSession().getAttribute("pendingExamDuration");
        }

        if (filePart == null || filePart.getSize() == 0) {
            byte[] pendingFileBytes = (byte[]) request.getSession().getAttribute("pendingExamFileBytes");
            String pendingFileName = (String) request.getSession().getAttribute("pendingExamFileName");
            if (pendingFileBytes != null && pendingFileBytes.length > 0) {
                filePart = new InMemoryPart("exam-file", pendingFileName, pendingFileBytes);
            }
        }

        Integer durationMinutes = parsePositiveInt(durationValue);

        if (title == null || title.isBlank() || deadlineValue == null || deadlineValue.isBlank() || filePart == null || durationMinutes == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing exam data (title, deadline, duration or file)");
            return;
        }

        String[] selectedStudentIds = request.getParameterValues("selectedStudentIds");
        List<Long> idStudents = new ArrayList<>();
        if (selectedStudentIds != null) {
            idStudents = Arrays.stream(selectedStudentIds)
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
        }

        Administrator admin = (Administrator) request.getSession().getAttribute("admin");
        LocalDate deadline = LocalDate.parse(deadlineValue);
        preparedExamService.createExam(title, deadline, durationMinutes, filePart, admin, idStudents);

        request.getSession().removeAttribute("pendingExamTitle");
        request.getSession().removeAttribute("pendingExamDeadline");
        request.getSession().removeAttribute("pendingExamDuration");
        request.getSession().removeAttribute("pendingExamFileBytes");
        request.getSession().removeAttribute("pendingExamFileName");

        response.sendRedirect(request.getContextPath() + "/afterLoginTeacher");
    }

    private Integer parsePositiveInt(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            int parsed = Integer.parseInt(value);
            return parsed > 0 ? parsed : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Implémentation simple de Part en mémoire.
     * Elle permet de reconstruire un fichier à partir des octets stockés en session.
     */
    private static class InMemoryPart implements Part {
        private final String name;
        private final String submittedFileName;
        private final byte[] content;

        /**
         * Crée un fichier en mémoire.
         *
         * @param name nom du champ multipart
         * @param submittedFileName nom original du fichier
         * @param content contenu binaire du fichier
         */
        private InMemoryPart(String name, String submittedFileName, byte[] content) {
            this.name = name;
            this.submittedFileName = submittedFileName;
            this.content = content;
        }

        @Override
        public InputStream getInputStream() {
            return new ByteArrayInputStream(content);
        }

        @Override
        public String getContentType() {
            return null;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getSubmittedFileName() {
            return submittedFileName;
        }

        @Override
        public long getSize() {
            return content.length;
        }

        @Override
        public void write(String fileName) {
            throw new UnsupportedOperationException("In-memory part write is not supported");
        }

        @Override
        public void delete() {
        }

        @Override
        public String getHeader(String name) {
            return null;
        }

        @Override
        public Collection<String> getHeaders(String name) {
            return List.of();
        }

        @Override
        public Collection<String> getHeaderNames() {
            return List.of();
        }
    }
}
