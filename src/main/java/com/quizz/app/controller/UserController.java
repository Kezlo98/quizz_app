package com.quizz.app.controller;

import com.quizz.app.request.PasswordUpdateRequest;
import com.quizz.app.dto.UserDto;
import com.quizz.app.entity.User;
import com.quizz.app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public String getUserProfile(Model model, Authentication authentication){
        String username = authentication.getName();
        User user = userService.getUserWithUserName(username);
        if(user != null){
            model.addAttribute("user",user);
        }

        return "userprofile";
    }

    @PostMapping
    public String updateUserProfile(@ModelAttribute UserDto userDto, Model model, Authentication authentication){
        String username = authentication.getName();
        userDto.setUsername(username);

        User user = userService.updateUser(userDto);
        if(user != null){
            model.addAttribute("user",user);
        }
        model.addAttribute("updateProfileSuccess", true);

        return "userprofile";
    }

    @PostMapping("/password")
    public String updatePassword(@ModelAttribute PasswordUpdateRequest passwordUpdateRequest, RedirectAttributes redirectAttributes, Authentication authentication){
        String error = validatePasswordUpdateRequest(passwordUpdateRequest);
        if(!StringUtils.hasLength(error)){
            String notification = userService.changePassword(passwordUpdateRequest, authentication.getName());
            redirectAttributes.addFlashAttribute("passwordChangeNotification",notification);
        }else{
            redirectAttributes.addFlashAttribute("passwordChangeError", error);
        }

        return "redirect:/user";

    }

    private String validatePasswordUpdateRequest(PasswordUpdateRequest passwordUpdateRequest){
        String currentPassword = passwordUpdateRequest.getCurrentPassword();
        String newPassword = passwordUpdateRequest.getNewPassword();
        String confirmNewPassword = passwordUpdateRequest.getConfirmNewPassword();

        if(!StringUtils.hasLength(currentPassword)|| !StringUtils.hasLength(newPassword) || !StringUtils.hasLength(confirmNewPassword)){
            return "All value is mandatory";
        }

        if(currentPassword.equalsIgnoreCase(newPassword)){
            return "New password must not be same as current";
        }

        if(!newPassword.equals(confirmNewPassword)){
            return "Confirm password is not match";
        }


        return "";
    }
}
