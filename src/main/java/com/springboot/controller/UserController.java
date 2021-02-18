package com.springboot.controller;

import com.springboot.entity.User;
import com.springboot.exception.EmailExistException;
import com.springboot.exception.UsernameExistException;
import com.springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({"/", "user"})
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/user/{username}")
    public String getUser(@PathVariable String username, Model model) {
        User user = this.userService.findUserByUsername(username);
        model.addAttribute("user", user);
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
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
