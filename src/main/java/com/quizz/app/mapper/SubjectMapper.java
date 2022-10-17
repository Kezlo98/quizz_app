package com.quizz.app.mapper;

import com.quizz.app.entity.Subject;
import com.quizz.app.request.SubjectDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectMapper {

  SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

  @Mapping(source = "subjectName", target = "name")
  Subject toEntity(SubjectDto request);
}
