package com.fsdm.examsmanagement.controller;

import com.fsdm.examsmanagement.dao.User.UserDAOImp;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
//import jakarta.inject.Inject;

@WebServlet("/afterLoginStudent")
public class AfterLoginStudent extends HttpServlet {

    @EJB
    UserDAOImp userDAOImp;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession(true);
        PrintWriter out = resp.getWriter();
        if(session ==null){
            req.getRequestDispatcher("/WEB-INF/jsp/AuthenticationError.jsp").forward(req, resp);
        }
        else{
//            session.setAttribute("user",new User(11L,"abderrahim","elouardi ","elouarid@gmail.com","2222"));
            req.getRequestDispatcher("/WEB-INF/jsp/AfterLoginJspPage.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException,IOException{
        doPost(req, resp);
    }
}
