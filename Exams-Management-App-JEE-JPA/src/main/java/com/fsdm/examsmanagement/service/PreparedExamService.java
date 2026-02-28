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
import java.util.ArrayList;
import java.util.List;

/**
 * Service qui prépare un examen.
 * Il crée l'examen, lit les questions et l'associe aux étudiants.
 */
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

    /**
     * Crée un examen puis ajoute ses questions et ses étudiants.
     *
     * @param titleExam titre de l'examen
     * @param deadline date limite de l'examen
     * @param responseTime durée de l'examen en minutes
     * @param filePart fichier contenant les questions
     * @param user utilisateur connecté (administrateur)
     * @param idStudents liste des ids des étudiants
     * @return true si l'opération est terminée
     */
    public boolean createExam(String titleExam, LocalDate deadline, Integer responseTime, Part filePart, User user, List<Long> idStudents) {
        Exam exam = new Exam();
        exam.setTitre(titleExam);
        exam.setDeadline(deadline);
        exam.setResponseTime(responseTime);
        Administrator administrator = (Administrator) user;
        exam.setAdmin(administrator);
        examDAO.save(exam);
        administrator.getExamList().add(exam);
        configureQuestion(exam, filePart);
        configureStudent(exam, idStudents);
        return true;
    }

    /**
     * Détermine le type de question selon le format de la ligne.
        *
        * @param ligne ligne lue depuis le fichier des questions
        * @return la stratégie de création adaptée, ou null si le format est inconnu
     */
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

    /**
     * Lit le fichier et enregistre chaque question reconnue.
        *
        * @param exam examen auquel les questions seront liées
        * @param filePart fichier uploadé contenant les questions
     */
    private void configureQuestion(Exam exam, Part filePart){
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(filePart.getInputStream()))) {
            reader.lines().forEach(ligne -> {
                CreateQuestioner createQuestioner = getTypeQuestion(ligne);
                if (createQuestioner != null) {
                    Questioner questioner = createQuestioner.construireQuestioner(ligne);
                    if (questioner != null) {
                        questioner.setExam(exam);
                        questionerDAOImp.save(questioner);
                    }
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Lie l'examen à tous les étudiants sélectionnés.
        *
        * @param exam examen à affecter
        * @param idStudents liste des identifiants des étudiants
     */
    private void configureStudent(Exam exam, List<Long> idStudents){
        for (Long idStudent : idStudents){
            Student student = studentDAO.findById(idStudent);

            if (student.getExamList() == null) {
                student.setExamList(new ArrayList<>());
            }
            student.getExamList().add(exam);

            ExamNote examNote = new ExamNote();
            examNote.setExam(exam);
            examNote.setStudent(student);
            examNote.setNote(0f);
            examNote.setStatus(false);

            if (student.getQuestions() == null) {
                student.setQuestions(new ArrayList<>());
            }
            student.getQuestions().add(examNote);

            if (exam.getExamStudent() == null) {
                exam.setExamStudent(new ArrayList<>());
            }
            exam.getExamStudent().add(examNote);

            studentDAO.save(student);
        }
    }
}
