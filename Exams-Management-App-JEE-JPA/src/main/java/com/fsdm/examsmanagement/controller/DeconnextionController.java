package com.fsdm.examsmanagement.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deconnection")
public class DeconnextionController extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        request.getSession(false).invalidate();
        request.getRequestDispatcher("").forward(request,response);
    }

}
