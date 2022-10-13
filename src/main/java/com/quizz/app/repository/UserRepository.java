package com.quizz.app.repository;

import com.quizz.app.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> getUserByUsername(String name);

  Optional<User> getUserByEmail(String email);

}
