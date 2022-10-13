package com.quizz.app.controller;

import com.quizz.app.entity.Subject;
import com.quizz.app.request.AddSubjectRequest;
import com.quizz.app.service.ISubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/subject")
@Slf4j
public class SubjectController {

  private ISubjectService subjectService;

  @Autowired
  public SubjectController(ISubjectService subjectService) {
    this.subjectService = subjectService;
  }

  @GetMapping
  public String subjectPage(Model model) {
    model.addAttribute("subjects",subjectService.getAllSubject());
    return "new-subject";
  }

  @PostMapping
  public String addSubject(@ModelAttribute AddSubjectRequest addSubjectRequest, RedirectAttributes redirectAttributes) {
    Subject subject = subjectService.addNewSubject(addSubjectRequest);
    if (subject == null) {
      redirectAttributes.addFlashAttribute("addNewFail", true);
    } else {
      redirectAttributes.addFlashAttribute("addNewSuccess", true);
    }
    return "redirect:/subject";
  }
}
