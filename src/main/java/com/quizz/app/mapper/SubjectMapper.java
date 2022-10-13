package com.quizz.app.mapper;

import com.quizz.app.entity.Subject;
import com.quizz.app.request.AddSubjectRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectMapper {

  SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

  Subject toEntity(AddSubjectRequest request);
}
