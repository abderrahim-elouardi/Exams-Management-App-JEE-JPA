package com.fsdm.examsmanagement.dao.student;

import com.fsdm.examsmanagement.model.Student;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class StudentDAOImp implements StudentDAO {
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
    public Student findByEmail(String email) {
        String jpql = "SELECT s FROM Student s WHERE s.email = :email";
        try {
            return em.createQuery(jpql, Student.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(Student element) {
        if (element.getId() == null) {
            em.persist(element);
        } else {
            em.merge(element); // Utilise merge si l'utilisateur existe déjà
        }
    }

    @Override
    public void delete(Student element) {
        Student toDelete = em.contains(element) ? element : em.merge(element);
        em.remove(toDelete);
    }

    @Override
    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        String jpql = "SELECT s FROM Student s ORDER BY s.id";
        return em.createQuery(jpql, Student.class).getResultList();
    }

    @Override
    public List<Student> findPaginated(int page, int pageSize) {
        return em.createQuery(
                        "SELECT s FROM Student s ORDER BY s.id",
                        Student.class
                )
                .setFirstResult((page - 1) * pageSize) // offset
                .setMaxResults(pageSize)               // limit
                .getResultList();
    }

    @Override
    public long countStudents() {
        return em.createQuery("SELECT COUNT(s) FROM Student s", Long.class)
                .getSingleResult();
    }
}
