package com.fsdm.examsmanagement.service;

import com.fsdm.examsmanagement.dao.exam.ExamDAO;
import com.fsdm.examsmanagement.dao.question.QuestionerDAOImp;
import com.fsdm.examsmanagement.dao.student.StudentDAO;
import com.fsdm.examsmanagement.model.*;
import com.fsdm.examsmanagement.strategy.CreateQuestioner;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.http.Part;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
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
    private StudentDAO studentDAO;
    public boolean createExam(String titleExam, LocalDate deadline, Part filePart, User user, List<Long> idStudents) {
        Exam exam = new Exam();
        exam.setTitre(titleExam);
        exam.setDeadline(deadline);
        Administrator administrator = (Administrator) user;
        exam.setAdmin(administrator);
        examDAO.save(exam);
        administrator.getExamList().add(exam);
        configureQuestion(exam, filePart);
        configureStudent(exam, idStudents);
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
                    if (questioner != null) {
                        questionerDAOImp.save(questioner);
                    }
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void configureStudent(Exam exam, List<Long> idStudents){
        for (Long idStudent : idStudents){
            Student student = studentDAO.findById(idStudent);
            student.getExamList().add(exam);
            studentDAO.save(student);
        }
    }
}
