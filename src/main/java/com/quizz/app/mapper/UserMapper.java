package com.quizz.app.mapper;

import com.quizz.app.dto.UserDto;
import com.quizz.app.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    public UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserDto userDto);

    UserDto toDto(User user);
}
