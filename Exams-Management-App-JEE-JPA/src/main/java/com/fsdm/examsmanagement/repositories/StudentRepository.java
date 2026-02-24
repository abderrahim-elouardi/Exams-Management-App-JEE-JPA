package com.fsdm.examsmanagement.repositories;

import com.fsdm.examsmanagement.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

public class StudentRepository{
     private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("myPU");
     private final EntityManager em;

     public StudentRepository() {
          this(ENTITY_MANAGER_FACTORY.createEntityManager());
     }

     public StudentRepository(EntityManager em) {
          this.em = em;
     }

     public Student findStudentByEmail(String email) {
          try {
               return em.createQuery(
                               "SELECT s FROM Student s WHERE s.email = :email", Student.class)
                       .setParameter("email", email)
                       .getSingleResult();
          } catch (NoResultException e) {
               return null;
          }
     }

     public Student saveStudent(Student student) {
          EntityTransaction transaction = em.getTransaction();
          try {
               transaction.begin();
               em.persist(student);
               transaction.commit();
               return student;
          } catch (RuntimeException exception) {
               if (transaction.isActive()) {
                    transaction.rollback();
               }
               throw exception;
          }
     }
}
