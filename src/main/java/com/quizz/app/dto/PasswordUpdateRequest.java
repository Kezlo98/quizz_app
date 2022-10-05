package com.quizz.app.dto;

import lombok.Data;

@Data
public class PasswordUpdateRequest {

    private String recoverEmail;
    private String resetPassword;
    private String confirmResetPassword;
}
