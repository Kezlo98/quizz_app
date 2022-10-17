package com.quizz.app.controller;

import static com.quizz.app.util.UserAttributeUtils.addUserAttribute;

import com.quizz.app.entity.Subject;
import com.quizz.app.request.SubjectDto;
import com.quizz.app.service.ISubjectService;
import com.quizz.app.util.WebPageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
  public String subjectPage(Model model, Authentication authentication) {
    addUserAttribute(model,authentication);
    model.addAttribute("subjects",subjectService.getAllSubject());
    return WebPageUtils.ALL_SUBJECT_PAGE;
  }

  @GetMapping("/new")
  @PreAuthorize("hasAuthority('ADMIN')")
  public String newSubjectPage(Model model, Authentication authentication) {
    addUserAttribute(model, authentication);
    return WebPageUtils.NEW_SUBJECT_PAGE;
  }

  @PostMapping
  public String addSubject(@ModelAttribute SubjectDto subjectDto, RedirectAttributes redirectAttributes) {
    Subject subject = subjectService.addNewSubject(subjectDto);
    if (subject == null) {
      redirectAttributes.addFlashAttribute("addNewFail", "Having error when adding new subject !!");
    } else {
      redirectAttributes.addFlashAttribute("addNewSuccess", "Add new Subject successfully !!");
    }
    return WebPageUtils.REDIRECT_TO_GET_SUBJECT;
  }

  @PostMapping("/update")
  public String updateSubject(@ModelAttribute SubjectDto subjectDto, RedirectAttributes redirectAttributes){
    try {
      subjectService.updateSubject(subjectDto);
      redirectAttributes.addFlashAttribute("updateSuccess", true);
    } catch (Exception e){
      redirectAttributes.addFlashAttribute("updateError", true);
    }

    return WebPageUtils.REDIRECT_TO_GET_SUBJECT;
  }

  @PostMapping("/status")
  public String changeStatus(@ModelAttribute("subjectId") Integer subjectId){
    subjectService.changeStatus(subjectId);

    return WebPageUtils.REDIRECT_TO_GET_SUBJECT;
  }
}
