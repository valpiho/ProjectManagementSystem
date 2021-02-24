package com.springboot.service;

import com.springboot.entity.User;
import com.springboot.exception.EmailExistException;
import com.springboot.exception.UsernameExistException;

import java.util.List;

public interface UserService {

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    List<User> findAllByUsernameNot(String username);

    void registerNewUser(String firstName, String lastName, String username, String email, String password)
            throws UsernameExistException, EmailExistException;

    void updateUserByUsername(String username, String newFirstName, String newLastName, String newUsername, String newEmail, String newPassword)
            throws UsernameExistException, EmailExistException;

    void deleteUserByUsername(String username);

    void resetPasswordByEmail(String email);


}
