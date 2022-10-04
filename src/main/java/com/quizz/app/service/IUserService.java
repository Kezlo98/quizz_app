package com.quizz.app.service;

import com.quizz.app.dto.UserDto;
import com.quizz.app.entity.User;

public interface IUserService {

    boolean isUsernameValid(String userName);

    User updatePasswordWithEmail(String email, String resetPassword);

    User createUser(UserDto user);

    boolean isLoginSuccess(UserDto userDto);

}
