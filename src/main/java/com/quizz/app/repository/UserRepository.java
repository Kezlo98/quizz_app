package com.quizz.app.repository;

import com.quizz.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> getUserByUsername(String name);

    Optional<User> getUserByEmail(String email);

}
