package com.fsdm.examsmanagement.dao.Exam;

import com.fsdm.examsmanagement.model.Exam;
import com.fsdm.examsmanagement.model.Student;
import com.fsdm.examsmanagement.model.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class ExamDAOImp implements ExamDAO {

    @PersistenceContext(unitName = "myPU")
    EntityManager em;

    @Override
    public Student findByEmailAndPassword(String email, String password) {
        String jpql = "SELECT a FROM Student a WHERE a.password = :password AND a.email = :email";

        try {
            return em.createQuery(jpql, Student.class)
                    .setParameter("password", password)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(Exam element) {
        if (element.getIdExam() == null) {
            em.persist(element);
        } else {
            em.merge(element); // Utilise merge si l'utilisateur existe déjà
        }
    }

    @Override
    public void delete(Exam element) {
        Exam toDelete = em.contains(element) ? element : em.merge(element);
        em.remove(toDelete);
    }

    @Override
    public Exam findById(Long id) {
        return em.find(Exam.class, id);
    }

    @Override
    public List<Exam> findAll() {
        String jpql = "SELECT u FROM User u";
        return em.createQuery(jpql, Exam.class).getResultList();
    }

    @Override
    public List<Exam> findPaginated(int page, int pageSize) {

        return em.createQuery(
                        "SELECT u FROM User u ORDER BY u.id",
                        Exam.class
                )
                .setFirstResult((page - 1) * pageSize) // offset
                .setMaxResults(pageSize)               // limit
                .getResultList();
    }
}
