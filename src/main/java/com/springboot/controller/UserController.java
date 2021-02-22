package com.springboot.controller;

import com.springboot.entity.User;
import com.springboot.exception.EmailExistException;
import com.springboot.exception.UsernameExistException;
import com.springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping({"/", "user", "error"})
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
        User user = userService.findUserByUsername(username);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/user/{username}/update")
    public String updateUser(@PathVariable String username, Model model) {
        User user = userService.findUserByUsername(username);
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("/user/{username}/updateForm")
    public String updateUser(@PathVariable(value = "username") String username,
                             @ModelAttribute(value = "user") User user) throws UsernameExistException, EmailExistException {
        userService.updateUserByUsername(username, user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail(), user.getPassword());
        return "profile";
    }

    @PostMapping("/user/{username}/delete")
    public String deleteUser(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return "redirect:/logout";
    }

    @GetMapping("/users")
    public String getUser( Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users-list";
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

    //TODO AT: loginError
    @GetMapping("/error")
    public String loginError (Model model) {
        model.addAttribute("loginError", true);
        return "/login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
