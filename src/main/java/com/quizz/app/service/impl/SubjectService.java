package com.quizz.app.service.impl;

import com.quizz.app.entity.Subject;
import com.quizz.app.mapper.SubjectMapper;
import com.quizz.app.repository.SubjectRepository;
import com.quizz.app.request.SubjectDto;
import com.quizz.app.service.ISubjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService implements ISubjectService {

  private String ACTIVE = "Active";
  private String INACTIVE = "Inactive";

  @Autowired
  private SubjectRepository subjectRepository;

  @Override
  public Subject addNewSubject(SubjectDto request) {
    Subject subject = SubjectMapper.INSTANCE.toEntity(request);
    subjectRepository.save(subject);

    return subject;
  }

  @Override
  public void updateSubject(SubjectDto subjectDto){
    Subject subject = subjectRepository.getById(subjectDto.getId());
    subject.setCategory(subjectDto.getCategory());
    subject.setFeatured(subjectDto.isFeatured());
    subject.setDuration(subjectDto.getDuration());
    subject.setDescription(subjectDto.getDescription());
    subject.setName(subjectDto.getSubjectName());
    subject.setPrice(subjectDto.getPrice());
    subject.setSalePrice(subjectDto.getSalePrice());
    subject.setStatus(subjectDto.getStatus());

    subjectRepository.save(subject);
  }

  @Override
  public List<Subject> getAllSubject(){
    return subjectRepository.findAll();
  }

  @Override
  public void changeStatus(Integer subjectId){
    Subject subject1 = subjectRepository.getById(subjectId);

    if(subject1.getStatus().equals(ACTIVE)){
      subject1.setStatus(INACTIVE);
    } else {
      subject1.setStatus(ACTIVE);
    }

    subjectRepository.save(subject1);
  }
}
