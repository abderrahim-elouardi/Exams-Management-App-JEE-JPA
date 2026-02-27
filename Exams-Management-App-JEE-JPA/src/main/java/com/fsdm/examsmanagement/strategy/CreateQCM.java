package com.fsdm.examsmanagement.strategy;

import com.fsdm.examsmanagement.model.QCM;
import com.fsdm.examsmanagement.model.QCMAnswer;
import com.fsdm.examsmanagement.model.Questioner;
import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class CreateQCM implements CreateQuestioner{
    @Override
    public Questioner construireQuestioner(String line) {
        String[] splitQuestion = line.split("\\|");
        if (splitQuestion.length < 3) {
            return null;
        }

        String question = splitQuestion[0];
        String[] responses = splitQuestion[1].split("\\,");
        int numberOfResponse = Integer.parseInt(splitQuestion[2]);

        QCM qcm = new QCM();
        qcm.setQuestion(question.trim());

        List<QCMAnswer> answerList = new ArrayList<>();
        for (int i = 0; i < responses.length; i++) {
            QCMAnswer qcmAnswer = new QCMAnswer();
            qcmAnswer.setAnswer(responses[i].trim());
            qcmAnswer.setStatus((i + 1) == numberOfResponse ? 1 : 0);
            qcmAnswer.setQcm(qcm);
            answerList.add(qcmAnswer);
        }

        qcm.setAnswerList(answerList);
        return qcm;
    }
}
