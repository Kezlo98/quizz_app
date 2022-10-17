package com.quizz.app.util;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class UserAttributeUtils {

  private UserAttributeUtils() {
  }

  public static void addUserAttribute(Model model, Authentication authentication){
    List<String> roles = new ArrayList<>();
    authentication.getAuthorities().forEach(grantedAuthority -> roles.add(grantedAuthority.getAuthority()));
    model.addAttribute("username", authentication.getName());
    model.addAttribute("roles", roles);
  }

  public static void addUserFlashAttributeForRedirect(RedirectAttributes attributeForRedirect, Authentication authentication){
    List<String> roles = new ArrayList<>();
    authentication.getAuthorities().forEach(grantedAuthority -> roles.add(grantedAuthority.getAuthority()));
    attributeForRedirect.addFlashAttribute("username",authentication.getName());
    attributeForRedirect.addFlashAttribute("roles",roles);
  }

}
