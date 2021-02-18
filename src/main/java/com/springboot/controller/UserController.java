package com.springboot.controller;

import com.springboot.entity.User;
import com.springboot.exception.EmailExistException;
import com.springboot.exception.UsernameExistException;
import com.springboot.service.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({"/", "user"})
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public UserController(UserService userService,
                          AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/user/{username}")
    public String getUser(@PathVariable String username) {
        return "profile";
    }

    @GetMapping("/register")
    public String registerUserForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/registerForm")
    public String registerUser(@ModelAttribute(value = "user") User user)
            throws UsernameExistException, EmailExistException {
        userService.registerNewUser(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail(), user.getPassword());
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginUserForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/loginForm")
    public String loginUser(@ModelAttribute(value = "user") User user) {
        authenticate(user.getUsername(), user.getPassword());
        if (isAuthenticated()) {
            return "redirect:/user/" + user.getUsername();
        }
        return "redirect:/login";
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }
}
