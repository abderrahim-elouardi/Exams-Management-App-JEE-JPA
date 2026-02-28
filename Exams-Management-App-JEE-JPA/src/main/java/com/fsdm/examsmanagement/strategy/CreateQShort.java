package com.fsdm.examsmanagement.strategy;

import com.fsdm.examsmanagement.model.Questioner;
import com.fsdm.examsmanagement.model.QShort;
import com.fsdm.examsmanagement.model.QShortAnswer;
import jakarta.ejb.Stateless;

/**
 * Cette classe crée une question à réponse courte.
 * Elle lit la question et la bonne réponse depuis une ligne de texte.
 */
@Stateless
public class CreateQShort implements CreateQuestioner{
    /**
     * Format attendu : question|bonneReponse
     * Plusieurs réponses possibles peuvent être séparées par ",,".
     */
    @Override
    public Questioner construireQuestioner(String line) {
        String[] splitQuestion = line.split("\\|");
        if (splitQuestion.length < 2) {
            return null;
        }

        String question = splitQuestion[0];
        String[] responses = splitQuestion[1].split("\\,,");

        QShort qShort = new QShort();
        qShort.setQuestion(question.trim());

        QShortAnswer qShortAnswer = new QShortAnswer();
        String expectedAnswer = responses.length > 0 ? responses[0].trim() : "";
        qShortAnswer.setAnswer(expectedAnswer);
        qShortAnswer.setStatus(1);
        qShortAnswer.setQshort(qShort);
        qShort.setAnswer(qShortAnswer);

        return qShort;
    }
}
