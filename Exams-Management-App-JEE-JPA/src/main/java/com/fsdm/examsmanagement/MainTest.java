package com.fsdm.examsmanagement;

import com.fsdm.examsmanagement.dao.User.UserDAOImp;
import com.fsdm.examsmanagement.dao.administrator.AdministratorDAOImp;
import com.fsdm.examsmanagement.dao.exam.ExamDAOImp;
import com.fsdm.examsmanagement.model.Administrator;
import com.fsdm.examsmanagement.model.Exam;
import com.fsdm.examsmanagement.model.User;
import jakarta.ejb.EJB;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.List;

public class MainTest {


    public static UserDAOImp userDAOImp;
    public static void main(String[] args){
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPU");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction entityTransaction = entityManager.getTransaction();
//
//        entityTransaction.begin();
//
//        Administrator user = new Administrator();
//        user.setEmail("elou@gmail.com");
//        user.setPassword("bonnn123");
//        user.setFirstName("elouardi");
//        user.setLastName("abderrahim");
//
//        entityManager.persist(user);
//        entityTransaction.commit();
//
//        entityManager.close();
//        entityManagerFactory.close();
//
//        com.fsdm.examsmanagement.dao.administrator.AdministratorDAOImp administratorDAOImp = new AdministratorDAOImp();
//        ExamDAOImp examDAOImp = new ExamDAOImp();
//        Administrator admin = new Administrator();
//        admin.setLastName("kamal");
//        admin.setFirstName("ismaili");
//        admin.setCin("cdd44444");
//
//        //exam1
//        Exam exam1 = new Exam();
//        exam1.setTitre("JEE Exam");
//        exam1.setDeadline(LocalDateTime.now().plusDays(5));
//        exam1.setAdmin(admin);
//        //exam2
//        Exam  exam2 = new Exam();
//        exam2.setTitre("JEE Exam");
//        exam2.setDeadline(LocalDateTime.now().plusDays(5));
//        exam2.setAdmin(admin);
//        //exam3
//        Exam  exam3 = new Exam();
//        exam3.setTitre("JEE Exam");
//        exam3.setDeadline(LocalDateTime.now().plusDays(5));
//        exam3.setAdmin(admin);
//        examDAOImp.save(exam1);
//        examDAOImp.save(exam2);
//        examDAOImp.save(exam3);
//        admin.setExamList(List.of(exam1 , exam2 ,exam3));
//        administratorDAOImp.save(admin);


    }
}
