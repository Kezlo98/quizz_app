package com.quizz.app.controller;

import com.quizz.app.dto.UserDto;
import com.quizz.app.entity.User;
import com.quizz.app.request.ResetPasswordRequest;
import com.quizz.app.service.IUserService;
import com.quizz.app.util.WebPageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {

  @Autowired
  private IUserService userService;

  @GetMapping
  public String getSignUpPage() {
    return WebPageUtils.SIGN_UP_PAGE;
  }

  @PostMapping
  public String signUp(@ModelAttribute UserDto user, Model model) {
    String signupError = null;

    if (user.getPassword() == null || !user.getPassword()
        .equalsIgnoreCase(user.getConfirmPassword())) {
      signupError = "The password and confirm password must match";
    }

    if (!userService.isUsernameValid(user.getUsername())) {
      signupError = "The username already exists.";
    }

    if (signupError == null) {
      User userResponse = userService.createUser(user);
      if (userResponse == null) {
        signupError = "There was an error signing you up. Please try again.";
      }
    }

    if (signupError == null) {
      model.addAttribute("signupSuccess", true);
      return WebPageUtils.REDIRECT_TO_GET_LOGIN_SUCCESS_SIGN_UP;
    }

    model.addAttribute("signupError", signupError);
    model.addAttribute("isResetPassword", false);
    return WebPageUtils.SIGN_UP_PAGE;
  }

  @PostMapping("/password")
  public String updatePassword(@ModelAttribute ResetPasswordRequest resetPasswordRequest,
      Model model) {

    String signupError = null;
    String resetPassword = resetPasswordRequest.getResetPassword();
    String confirmResetPassword = resetPasswordRequest.getConfirmResetPassword();
    String recoverEmail = resetPasswordRequest.getRecoverEmail();

    if (resetPassword == null || !resetPassword.equalsIgnoreCase(confirmResetPassword)) {
      signupError = "The password and confirm password must match";
    }

    if (signupError == null) {
      User userResponse = userService.updatePasswordWithEmail(recoverEmail, resetPassword);
      if (userResponse == null) {
        signupError = "Please double check the email";
      }
    }

    model.addAttribute("isResetPassword", false);
    if (signupError == null) {
      return WebPageUtils.REDIRECT_TO_GET_LOGIN_SUCCESS_RESET_PASSWORD;
    } else {
      model.addAttribute("signupError", signupError);
    }
    return WebPageUtils.SIGN_UP_PAGE;
  }
}
