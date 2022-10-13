package com.quizz.app.service;

import com.quizz.app.entity.Subject;
import com.quizz.app.request.AddSubjectRequest;
import java.util.List;

public interface ISubjectService {

  Subject addNewSubject(AddSubjectRequest request);

  List<Subject> getAllSubject();
}
