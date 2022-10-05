package com.quizz.app.controller;

import com.quizz.app.dto.UserDto;
import com.quizz.app.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {

    private IUserService userService;

    @GetMapping
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/successfulSignup")
    public String getLoginPageAfterSignupSuccess(Model model){

        model.addAttribute("signupSuccess", true);

        return "/login";
    }

    @GetMapping("/successfulResetPassword")
    public String getLoginPageAfterResetPasswordSuccess(Model model, RedirectAttributes redirectAttributes){
        
        redirectAttributes.addFlashAttribute("resetSuccess", true);

        return "redirect:/login";
    }
}
