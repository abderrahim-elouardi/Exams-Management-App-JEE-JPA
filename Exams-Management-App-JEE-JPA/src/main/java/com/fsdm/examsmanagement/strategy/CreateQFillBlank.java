package com.fsdm.examsmanagement.strategy;

import com.fsdm.examsmanagement.model.QFillInBlank;
import com.fsdm.examsmanagement.model.Questioner;
import jakarta.ejb.Stateless;

/**
 * Cette classe crée une question à trou (remplir le blanc).
 * Le texte reçu devient directement l'énoncé de la question.
 */
@Stateless
public class CreateQFillBlank implements CreateQuestioner{
    /**
     * Construit une question à trou à partir d'une ligne simple.
     */
    @Override
    public Questioner construireQuestioner(String line) {
        QFillInBlank qFillInBlank = new QFillInBlank();
        qFillInBlank.setQuestion(line);
        return  qFillInBlank;
    }
}
