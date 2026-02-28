package com.fsdm.examsmanagement.dao.examnote;

import com.fsdm.examsmanagement.model.ExamNote;

import java.util.List;

public interface ExamNoteDAO {
    int markAsPassed(Long studentId, Long examId);
    int saveExamResult(Long studentId, Long examId, float note, boolean status);
    List<ExamNote> findPassedByStudentId(Long studentId);
}
