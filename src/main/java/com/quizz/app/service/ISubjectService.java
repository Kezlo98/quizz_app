package com.quizz.app.service;

import com.quizz.app.entity.Subject;
import com.quizz.app.request.SubjectDto;
import java.util.List;

public interface ISubjectService {

  Subject addNewSubject(SubjectDto request);

  void updateSubject(SubjectDto subjectDto);

  List<Subject> getAllSubject();

  void changeStatus(Integer subjectId);
}
