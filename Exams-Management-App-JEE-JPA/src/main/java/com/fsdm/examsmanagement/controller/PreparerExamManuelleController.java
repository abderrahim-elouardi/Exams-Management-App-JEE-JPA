package com.fsdm.examsmanagement.controller;

import com.fsdm.examsmanagement.dao.administrator.AdministratorDAO;
import com.fsdm.examsmanagement.dao.exam.ExamDAO;
import com.fsdm.examsmanagement.model.*;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/preparerExamManuelleController")
public class PreparerExamManuelleController extends HttpServlet {


    @EJB
    AdministratorDAO administratorDAO;
    @EJB
    ExamDAO examDAO;

    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();
        String titre = request.getParameter("exam_title");
        String description = request.getParameter("exam_description");
        String deadline = request.getParameter("exam_date");
        out.println(titre);
        out.println(description);
        out.println(deadline);
        String[] splitedDeadline = deadline.split("-");
        String yearDeadline = splitedDeadline[0];
        String mounthDeadline = splitedDeadline[1];
        String dayDeadline = splitedDeadline[2];
        out.println("deadline is :"+deadline);
        String QCMquestion_typesCheckBox = request.getParameter("QCMquestion_typesCheckBox");
        String SHORTquestion_typesCheckBox = request.getParameter("SHORTquestion_typesCheckBox");
        String FILLBLANKquestion_typesCheckBox = request.getParameter("FILLBLANKquestion_typesCheckBox");


        int numberOfQcmQuestion = Integer.parseInt(request.getParameter("numberOfQcmQuestion"));
        int numberOfShortAnswerQuestion = Integer.parseInt(request.getParameter("numberOfShortAnswerQuestion"));
        int numberOfFillBlankQuestion = Integer.parseInt(request.getParameter("numberOfFillBlankQuestion"));

        Exam exam=new Exam();
        exam.setTitre(titre);
        exam.setDeadline(LocalDate.of(Integer.parseInt(yearDeadline),Integer.parseInt(mounthDeadline),Integer.parseInt(dayDeadline)));

        if(QCMquestion_typesCheckBox!=null){
            for(int i=0;i<numberOfQcmQuestion;i++){
                QCM qcm = new QCM();
                qcm.setQuestion(request.getParameter("QCMquestion_"+i));


                QCMAnswer optionAnswer1 = new QCMAnswer();
                optionAnswer1.setAnswer(request.getParameter("QCMq"+i+"_opt1"));

                QCMAnswer optionAnswer2 = new QCMAnswer();
                optionAnswer2.setAnswer(request.getParameter("QCMq"+i+"_opt2"));

                QCMAnswer optionAnswer3 = new QCMAnswer();
                optionAnswer3.setAnswer(request.getParameter("QCMq"+i+"_opt3"));


                QCMAnswer optionAnswer4 = new QCMAnswer();
                optionAnswer4.setAnswer(request.getParameter("QCMq"+i+"_opt4"));


                qcm.setAnswerList(List.of(optionAnswer1,optionAnswer2,optionAnswer3,optionAnswer4));

                if(exam.getQuestioner()==null){
                    List<Questioner> list = new ArrayList<>();
                    list.add(qcm);
                    exam.setQuestioner(list);
                }
                else{
                    exam.getQuestioner().add(qcm);
                }
            }
        }
        if(SHORTquestion_typesCheckBox!=null){
            for(int i=0;i<numberOfShortAnswerQuestion;i++){
                QShort qshort = new QShort();
                qshort.setQuestion(request.getParameter("ShortAnswerquestion_"+i));
                QShortAnswer answer = new QShortAnswer();
                answer.setAnswer(request.getParameter("ShortAnswerq"+i+"_opt1"));
                answer.setQshort(qshort);
                answer.setStatus(1);
                qshort.setAnswer(answer);
                if(exam.getQuestioner()==null){
                    List<Questioner> list = new ArrayList<>();
                    list.add(qshort);
                    exam.setQuestioner(list);
                }
                else{
                    exam.getQuestioner().add(qshort);
                }
            }
        }
        if(FILLBLANKquestion_typesCheckBox!=null){
            for(int i=0;i<numberOfFillBlankQuestion;i++){
                QFillInBlank qFillInBlank = new QFillInBlank();
                qFillInBlank.setQuestion(request.getParameter("FillBlankquestion_"+i));

                String answers = request.getParameter("FillBlankq"+i+"_opt1");
                List<QFillInBlankAnswer> listAnswers = new ArrayList<>();
                for(String answer:answers.split(":")){
                    QFillInBlankAnswer qFillInBlankAnswer = new QFillInBlankAnswer();
                    qFillInBlankAnswer.setAnswer(answer);
                    listAnswers.add(qFillInBlankAnswer);
                }
                qFillInBlank.setQFillInBlankAnswer(listAnswers);

                if(exam.getQuestioner()==null){
                    List<Questioner> list = new ArrayList<>();
                    list.add(qFillInBlank);
                    exam.setQuestioner(list);
                }
                else{
                    exam.getQuestioner().add(qFillInBlank);
                }
            }
        }
        Administrator admin = (Administrator) request.getSession().getAttribute("admin");
        exam.setAdmin(admin);
        examDAO.save(exam);
        admin.getExamList().add(exam);

        request.getRequestDispatcher("/convocationPage.jsp").forward(request, response);
    }
}
