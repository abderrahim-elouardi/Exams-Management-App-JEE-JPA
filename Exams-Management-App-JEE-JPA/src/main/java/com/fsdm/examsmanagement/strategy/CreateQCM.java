package com.fsdm.examsmanagement.strategy;

import com.fsdm.examsmanagement.model.QCM;
import com.fsdm.examsmanagement.model.QCMAnswer;
import com.fsdm.examsmanagement.model.Questioner;
import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe crée une question de type QCM.
 * Elle transforme une ligne de texte en question avec ses choix.
 */
@Stateless
public class CreateQCM implements CreateQuestioner{
    /**
     * Format attendu : question|choix1,choix2,choix3|numeroBonneReponse
     * Exemple : "Capital du Maroc?|Rabat,Casa,Tanger|1"
     */
    @Override
    public Questioner construireQuestioner(String line) {
        String[] splitQuestion = line.split("\\|");
        if (splitQuestion.length < 3) {
            return null;
        }

        String question = splitQuestion[0];
        String[] responses = splitQuestion[1].split("\\,");
        String[] numberOfResponse = splitQuestion[2].split("\\,");

        QCM qcm = new QCM();
        qcm.setQuestion(question.trim());

        List<QCMAnswer> answerList = new ArrayList<>();
        for (int i = 0; i < responses.length; i++) {
            QCMAnswer qcmAnswer = new QCMAnswer();
            qcmAnswer.setAnswer(responses[i].trim());
            int status = 0;
            for (String num : numberOfResponse) {
                if ((i + 1) == Integer.parseInt(num.trim())) {
                    status = 1;
                    break;
                }
            }
            qcmAnswer.setQcm(qcm);
            answerList.add(qcmAnswer);
        }

        qcm.setAnswerList(answerList);
        return qcm;
    }
}
