package com.quizz.app.request;

import lombok.Data;

@Data
public class PasswordUpdateRequest {

    private String currentPassword;
    private String newPassword;
    private String confirmNewPassword;
}
