package com.cm.cm2.services;

import java.util.List;
import java.util.Optional;

import com.cm.cm2.entities.User;

public interface UserService {

    User saveUser(User user);

    Optional<User> getUserById(String id);

    Optional<User> updateUser(User user);

    void deleteUser(String id);

    boolean isUserExist(String Id);

    boolean isUserExistByEmail(String email);

    List<User> getAllUser();

    User getUserByEmail(String email);

    User updatePasswordByuserId(String id, String password);

    Optional<User> getUserByToken(String token);

    Optional<User> getUserByForgetToken(String token);

}
