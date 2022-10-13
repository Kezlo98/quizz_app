package com.quizz.app.service.impl;

import com.quizz.app.entity.Subject;
import com.quizz.app.mapper.SubjectMapper;
import com.quizz.app.repository.SubjectRepository;
import com.quizz.app.request.AddSubjectRequest;
import com.quizz.app.service.ISubjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService implements ISubjectService {

  @Autowired
  private SubjectRepository subjectRepository;

  @Override
  public Subject addNewSubject(AddSubjectRequest request) {
    Subject subject = SubjectMapper.INSTANCE.toEntity(request);
    subjectRepository.save(subject);

    return subject;
  }

  @Override
  public List<Subject> getAllSubject(){
    return subjectRepository.findAll();
  }
}
