package com.cm.cm2.repsitories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cm.cm2.entities.User;

@Repository

public interface UserRepo extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByEmailToken(String emailToken);

    Optional<User> findByForgetpasswordToken(String forgetpasswordToken);
}
