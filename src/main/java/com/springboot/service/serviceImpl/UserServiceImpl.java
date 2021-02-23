package com.springboot.service.serviceImpl;

import com.springboot.entity.User;
import com.springboot.entity.UserPrincipal;
import com.springboot.enumeration.Role;
import com.springboot.exception.EmailExistException;
import com.springboot.exception.UsernameExistException;
import com.springboot.repository.UserRepository;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@Qualifier("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username is not found");
        }
        return new UserPrincipal(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void registerNewUser(String firstName, String lastName, String username, String email, String password)
            throws UsernameExistException, EmailExistException {
        validateUsernameAndEmail(username, email);
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRole(Role.ROLE_USER.name());
        user.setAuthorities(Role.ROLE_USER.getAuthorities());
        user.setEnabled(true);
        user.setCreatedAt(new Date());
        userRepository.save(user);
    }

    @Override
    public void updateUserByUsername(String username, String newFirstName, String newLastName, String newUsername, String newEmail, String newPassword) throws UsernameExistException, EmailExistException {
        User user = userRepository.findUserByUsername(username);
        validateUsernameAndEmail(newUsername, newEmail);
        user.setFirstName(newFirstName);
        user.setLastName(newLastName);
        user.setUsername(newUsername);
        user.setEmail(newEmail);
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        user.setRole(Role.ROLE_USER.name());
        user.setAuthorities(Role.ROLE_USER.getAuthorities());
        user.setUpdatedAt(new Date());
        userRepository.save(user);
    }

    @Override
    public void deleteUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        userRepository.delete(user);
    }

    @Override
    public void resetPasswordByEmail(String email) {
        // TODO: User password reset
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    private void validateUsernameAndEmail(String username, String email)
            throws UsernameExistException, EmailExistException {
        if (findUserByUsername(username) != null) {
            throw new UsernameExistException("Username already exists");
        }
        if (findUserByEmail(email) != null) {
            throw new EmailExistException("Email already exists");
        }
    }
}
