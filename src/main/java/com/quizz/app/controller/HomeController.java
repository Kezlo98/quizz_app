package com.quizz.app.controller;

import static com.quizz.app.util.UserAttributeUtils.addUserAttribute;

import com.quizz.app.util.WebPageUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

  @GetMapping
  public String homePage(Model model, Authentication authentication) {
    addUserAttribute(model,authentication);
    return WebPageUtils.HOME_PAGE;
  }
}
