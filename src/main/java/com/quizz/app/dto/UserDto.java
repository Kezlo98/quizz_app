package com.quizz.app.dto;

import com.quizz.app.entity.RoleEnum;
import lombok.Data;

@Data
public class UserDto {

    private String username;
    private String firstName;
    private String lastName;
    private String universityName;
    private String description;
    private String password;
    private String confirmPassword;
    private String email;
    private RoleEnum role;
}
