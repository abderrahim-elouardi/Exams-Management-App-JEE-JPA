package com.fsdm.examsmanagement.controller;

import com.fsdm.examsmanagement.dao.administrator.AdministratorDAO;
import com.fsdm.examsmanagement.dao.administrator.AdministratorDAOImp;
import com.fsdm.examsmanagement.dao.exam.ExamDAO;
import com.fsdm.examsmanagement.dao.exam.ExamDAOImp;
import com.fsdm.examsmanagement.dao.student.StudentDAO;
import com.fsdm.examsmanagement.model.Administrator;
import com.fsdm.examsmanagement.model.Exam;
import com.fsdm.examsmanagement.model.Student;
import jakarta.ejb.EJB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/createData")
public class DataCreationServlet extends HttpServlet {

    @EJB
    ExamDAO examDAO;
    @EJB
    AdministratorDAO administratorDAO;
    @EJB
    StudentDAO studentDAO;
    @Override
    public void doGet(HttpServletRequest request , HttpServletResponse response){

        // 1. Créer et SAUVEGARDER l'administrateur en premier
        Administrator admin = new Administrator();
        admin.setEmail("username");
        admin.setPassword("password");
        admin.setLastName("kamal");
        admin.setFirstName("ismaili");
        admin.setCin("cdd44444");

        // On persiste l'admin pour qu'il reçoive un ID de la base
        administratorDAO.save(admin);

        // 2. Créer les examens en utilisant l'admin déjà sauvegardé
        Exam exam1 = new Exam();
        exam1.setTitre("JEE Exam 1");
        exam1.setDeadline(LocalDateTime.now().plusDays(5));
        exam1.setAdmin(admin);

        Exam exam2 = new Exam();
        exam2.setTitre("JEE Exam 2");
        exam2.setDeadline(LocalDateTime.now().plusDays(5));
        exam2.setAdmin(admin);

        // 3. Sauvegarder les examens
        examDAO.save(exam1);
        examDAO.save(exam2);

        // Optionnel : Mettre à jour la liste côté Java (pour la cohérence de l'objet en mémoire)
        admin.setExamList(List.of(exam1, exam2));


        System.out.println("--- l'enregeistrement bien effectue ------------");
        for(Exam exam : examDAO.findAll()){
            System.out.println(exam);
        }

        Student student = new Student();
        student.setCne("n13245567");
        student.setEmail("username");
        student.setPassword("password");
        student.setFirstName("abderrahim");
        student.setLastName("el ouardi");


        student.setExamList(List.of(exam1,exam2));
        studentDAO.save(student);
        System.out.println("student was saved");
    }


}
