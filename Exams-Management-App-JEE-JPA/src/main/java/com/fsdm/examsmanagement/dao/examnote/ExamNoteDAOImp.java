package com.fsdm.examsmanagement.dao.examnote;

import com.fsdm.examsmanagement.model.ExamNote;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class ExamNoteDAOImp implements ExamNoteDAO {

    @PersistenceContext(unitName = "myPU")
    private EntityManager em;

    @Override
    public int markAsPassed(Long studentId, Long examId) {
        return em.createQuery(
                        "UPDATE ExamNote en SET en.status = true WHERE en.student.id = :studentId AND en.exam.idExam = :examId"
                )
                .setParameter("studentId", studentId)
                .setParameter("examId", examId)
                .executeUpdate();
    }

    @Override
    public int saveExamResult(Long studentId, Long examId, float note, boolean status) {
        return em.createQuery(
                        "UPDATE ExamNote en SET en.status = :status, en.note = :note WHERE en.student.id = :studentId AND en.exam.idExam = :examId"
                )
                .setParameter("status", status)
                .setParameter("note", note)
                .setParameter("studentId", studentId)
                .setParameter("examId", examId)
                .executeUpdate();
    }

    @Override
    public List<ExamNote> findPassedByStudentId(Long studentId) {
        return em.createQuery(
                        "SELECT en FROM ExamNote en JOIN FETCH en.exam WHERE en.student.id = :studentId AND en.status = true",
                        ExamNote.class
                )
                .setParameter("studentId", studentId)
                .getResultList();
    }
}
