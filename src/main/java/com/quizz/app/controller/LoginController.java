package com.quizz.app.controller;

import com.quizz.app.util.WebPageUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {

  @GetMapping
  public String getLoginPage() {
    return WebPageUtils.LOGIN_PAGE;
  }

  @GetMapping("/successfulSignup")
  public String getLoginPageAfterSignupSuccess(RedirectAttributes redirectAttributes) {

    redirectAttributes.addFlashAttribute("signupSuccess", true);

    return WebPageUtils.REDIRECT_TO_GET_LOGIN;
  }

  @GetMapping("/successfulResetPassword")
  public String getLoginPageAfterResetPasswordSuccess(RedirectAttributes redirectAttributes) {

    redirectAttributes.addFlashAttribute("resetSuccess", true);

    return WebPageUtils.REDIRECT_TO_GET_LOGIN;
  }
}
