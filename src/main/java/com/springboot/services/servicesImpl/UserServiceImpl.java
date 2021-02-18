package com.springboot.services.servicesImpl;

import com.springboot.entity.User;
import com.springboot.repository.UserRepository;
import com.springboot.services.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByUsername(String username) {
        // TODO: Find User by username
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        // TODO: Find User by email
        return null;
    }

    @Override
    public void registerNewUser(String firstName, String lastName, String username, String Email, String Password) {
        // TODO: User registration
    }

    @Override
    public void login() {
        // TODO: User login
    }

    @Override
    public User updateUserById(Long id, String firstName, String lastName, String username, String Email, String Password) {
        // TODO: User update
        return null;
    }

    @Override
    public void deleteUserById(Long id) {
        // TODO: User delete
    }

    @Override
    public void resetPasswordByEmail(String email) {
        // TODO: User password reset
    }

    @Override
    public List<User> findAllUsers() {
        // TODO: Find all Users
        return null;
    }
}
