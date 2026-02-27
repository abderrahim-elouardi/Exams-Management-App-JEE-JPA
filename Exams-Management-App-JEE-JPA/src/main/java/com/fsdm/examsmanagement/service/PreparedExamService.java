package com.fsdm.examsmanagement.service;

import com.fsdm.examsmanagement.dao.core.AbstractGeneriqueDAO;
import com.fsdm.examsmanagement.dao.exam.ExamDAO;
import com.fsdm.examsmanagement.dao.question.QAnswerDAOImp;
import com.fsdm.examsmanagement.dao.question.QuestionerDAOImp;
import com.fsdm.examsmanagement.dao.student.StudentDAOImp;
import com.fsdm.examsmanagement.model.*;
import com.fsdm.examsmanagement.strategy.CreateQuestioner;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.http.Part;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Stateless
public class PreparedExamService {
    @EJB(beanName = "CreateQCM")
    private CreateQuestioner createQCM;
    @EJB(beanName = "CreateQShort")
    private CreateQuestioner createQShort;
    @EJB(beanName = "CreateQFillBlank")
    private CreateQuestioner createQFillBlank;
    @EJB
    private ExamDAO examDAO;
    @EJB
    private QuestionerDAOImp questionerDAOImp;
    @EJB
    private StudentDAOImp studentDAOImp;
    public boolean createExam(String titleExam, LocalDate deadline, Part filePart, User user) {
        Exam exam = new Exam();
        exam.setTitre(titleExam);
        exam.setDeadline(deadline);
        exam.setAdmin((Administrator) user);
        examDAO.save(exam);
        return true;
    }
    private CreateQuestioner getTypeQuestion(String ligne){
        String qcmPattern = "^.+\\|.+(,.+)+\\|\\d+$";
        String trouPattern = ".*\\.\\.\\.\\.\\.\\..*";
        String courtePattern = "^.+\\|.+(,,.+)+$";
        if (ligne.matches(qcmPattern)) {
            return createQCM;
        }
        if (ligne.matches(trouPattern)) {
            return createQFillBlank;
        }
        if (ligne.matches(courtePattern)) {
            return createQShort;
        }
        return null;
    }
    private void configureQuestion(Exam exam, Part filePart){
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(filePart.getInputStream()))) {
            reader.lines().forEach(ligne -> {
                CreateQuestioner createQuestioner = getTypeQuestion(ligne);
                if (createQuestioner != null) {
                    Questioner questioner = createQuestioner.construireQuestioner(ligne);
                    questioner.setExam(exam);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void configureStudent(Exam exam, List<Long> idStudents){
        for (Long idStudent : idStudents){
            Student student = studentDAOImp.findById(idStudent);
            student.getExamList().add(exam);
            studentDAOImp.save(student);
        }
    }
}
