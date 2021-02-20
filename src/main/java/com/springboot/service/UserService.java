package com.springboot.service;

import com.springboot.entity.User;
import com.springboot.exception.EmailExistException;
import com.springboot.exception.UsernameExistException;

import java.util.List;

public interface UserService {

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    void registerNewUser(String firstName, String lastName, String username, String email, String password)
            throws UsernameExistException, EmailExistException;

    void updateUserById(Long id, String firstName, String lastName, String username, String email, String password );

    void deleteUserById(Long id);

    void resetPasswordByEmail(String email);

    List<User> findAllUsers();
}
