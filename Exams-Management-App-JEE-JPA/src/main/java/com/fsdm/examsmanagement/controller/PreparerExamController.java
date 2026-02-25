package com.fsdm.examsmanagement.controller;

import jakarta.jws.WebService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;

@WebServlet("/preparerExamController")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 10,  // 10MB
        maxRequestSize = 1024 * 1024 * 50
)
public class PreparerExamController extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Titre:"+request.getParameter("exam-title"));
        out.println("deadline:"+request.getParameter("exam-deadline"));
        out.println("Questions File:"+request.getParameter("exam-file"));

        Part filePart = request.getPart("exam-file");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(filePart.getInputStream())
        );
        reader.lines().forEach(out::println);
    }
}
