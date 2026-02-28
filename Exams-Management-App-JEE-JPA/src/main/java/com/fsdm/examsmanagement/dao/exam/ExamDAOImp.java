package com.fsdm.examsmanagement.dao.exam;

import com.fsdm.examsmanagement.model.Exam;
import com.fsdm.examsmanagement.model.Student;
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
    public Exam findExamWithDetails(Long idExam) {
        String examWithQuestionsJpql = "SELECT DISTINCT e FROM Exam e " +
            "LEFT JOIN FETCH e.questioner " +
            "WHERE e.idExam = :idExam";

        try {
            Exam exam = em.createQuery(examWithQuestionsJpql, Exam.class)
                    .setParameter("idExam", idExam)
                    .getSingleResult();

            List<Student> students = em.createQuery(
                    "SELECT DISTINCT s FROM Student s JOIN s.examList e WHERE e.idExam = :idExam",
                    Student.class
                )
                .setParameter("idExam", idExam)
                .getResultList();

            exam.setStudentList(students);
            return exam;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Exam> findAll() {
        String jpql = "SELECT u FROM Exam u";
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

    public List<Exam> findByStudent(Student s) {
        String jpql = "SELECT a FROM Exam a WHERE a.studentList = :student";

        try {
            return em.createQuery(jpql, Exam.class)
                    .setParameter("student", s)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
