package com.quizz.app.service;

import com.quizz.app.dto.PasswordUpdateRequest;
import com.quizz.app.dto.UserDto;
import com.quizz.app.entity.User;

public interface IUserService {

    boolean isUsernameValid(String userName);

    User updatePasswordWithEmail(String email, String resetPassword);

    User createUser(UserDto user);

    boolean isLoginSuccess(UserDto userDto);

    User getUserWithUserName(String userName);

    User updateUser(UserDto userDto);

    String changePassword(PasswordUpdateRequest request, String userName);
}
