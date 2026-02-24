package com.fsdm.examsmanagement;

import com.fsdm.examsmanagement.dao.User.UserDAOImp;
import com.fsdm.examsmanagement.model.Administrator;
import com.fsdm.examsmanagement.model.User;
import jakarta.ejb.EJB;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

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

        userDAOImp = new UserDAOImp();
        User user1 = userDAOImp.findByEmailAndPassword("elou@gmail.com","bonnn123");
        System.out.println(user1.getEmail());

    }
}
