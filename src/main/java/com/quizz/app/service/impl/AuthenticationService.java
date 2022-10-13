package com.quizz.app.service.impl;

import com.quizz.app.entity.Role;
import com.quizz.app.entity.User;
import com.quizz.app.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements AuthenticationProvider {

  @Autowired
  private UserRepository userRepository;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    String username = authentication.getName();
    String password = authentication.getCredentials().toString();

    Optional<User> userOptional = userRepository.getUserByUsername(username);
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      List<SimpleGrantedAuthority> authorities = new ArrayList<>();
      for (Role role : user.getRoles()) {
        authorities.add(new SimpleGrantedAuthority(role.getRole().getRole()));
      }
      if (user.getPassword().equalsIgnoreCase(password)) {
        return new UsernamePasswordAuthenticationToken(username, password, authorities);
      }
    }

    return null;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
