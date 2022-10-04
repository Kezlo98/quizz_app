package com.quizz.app.controller;

import com.quizz.app.dto.UserDto;
import com.quizz.app.entity.User;
import com.quizz.app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/signup")
public class SignUpController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public String getSignUpPage() {
        return "signup";
    }

    @PostMapping
    public String signUp(@ModelAttribute UserDto user, Model model) {
        String signupError = null;

        if(user.getPassword() == null || !user.getPassword().equalsIgnoreCase(user.getConfirmPassword())){
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
            return "redirect:/api/login/successfulSignup";
        }

        model.addAttribute("signupError", signupError);
        model.addAttribute("isResetPassword", false);
        return "signup";
    }

    @GetMapping("/password/reset")
    public String getResetPasswordForm(@ModelAttribute String recoverEmail, Model model){
        model.addAttribute("isResetPassword", true);
        model.addAttribute("recoverEmail",recoverEmail);
        return "signup";
    }

    @PostMapping("/password/reset")
    public String resetPassword(@ModelAttribute String recoverEmail, @ModelAttribute String resetPassword, @ModelAttribute String confirmResetPassword, Model model){

        String signupError = null;

        if(resetPassword == null || !resetPassword.equalsIgnoreCase(confirmResetPassword)){
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
            return "redirect:api/login/successfulResetPassword";
        } else {
            model.addAttribute("signupError", signupError);
        }
        return "signup";
    }
}
