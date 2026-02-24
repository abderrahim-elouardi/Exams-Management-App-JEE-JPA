package com.fsdm.examsmanagement;

import com.fsdm.examsmanagement.model.Administrator;
import com.fsdm.examsmanagement.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class MainTest {
    public static void main(String[] args){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Administrator user = new Administrator();
        user.setEmail("elou@gmail.com");
        user.setPassword("bonnn123");
        user.setFirstName("elouardi");
        user.setLastName("abderrahim");

        entityManager.persist(user);
        entityTransaction.commit();

        entityManager.close();
        entityManagerFactory.close();
        while(true){

        }
    }
}
