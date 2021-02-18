package com.springboot.services;

import com.springboot.entity.User;

import java.util.List;

public interface UserService {

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    void registerNewUser(String firstName, String lastName, String username, String Email, String Password);

    void login();

    User updateUserById(Long id, String firstName, String lastName, String username, String Email, String Password );

    void deleteUserById(Long id);

    void resetPasswordByEmail(String email);

    List<User> findAllUsers();
}
