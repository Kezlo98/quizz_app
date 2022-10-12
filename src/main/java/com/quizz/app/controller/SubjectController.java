package com.quizz.app.controller;

import com.quizz.app.request.AddSubjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/subject")
@Slf4j
public class SubjectController {

    @GetMapping
    public String subjectPage(Model model){
        return "new-subject";
    }

    @PostMapping
    public String addSubject(@ModelAttribute AddSubjectRequest addSubjectRequest, Model model){
        log.info(addSubjectRequest.toString());
        return "new-subject";
    }
}
