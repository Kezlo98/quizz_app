package com.quizz.app.dto;

import com.quizz.app.entity.RoleEnum;
import lombok.Data;

@Data
public class UserDto {

    private String userName;
    private String password;
    private String confirmPassword;
    private String email;
    private RoleEnum role;
}
