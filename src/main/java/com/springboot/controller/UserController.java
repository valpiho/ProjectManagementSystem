package com.springboot.controller;

import com.springboot.entity.Project;
import com.springboot.entity.Task;
import com.springboot.entity.User;
import com.springboot.exception.EmailExistException;
import com.springboot.exception.UsernameExistException;
import com.springboot.service.ProjectService;
import com.springboot.service.TaskService;
import com.springboot.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping({"/", "user"})
public class UserController {

    private final UserService userService;
    private final ProjectService projectService;
    private final TaskService taskService;

    public UserController(UserService userService,
                          ProjectService projectService,
                          TaskService taskService) {
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String indexPage(Model model, Authentication authentication) {
        if (authentication != null) {
            User user = getUser(authentication);
            model.addAttribute("user", user);
        }
        return "index";
    }

    @GetMapping("/user")
    public String getUser(Model model, Authentication authentication) {
        User user = getUser(authentication);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/user/{username}")
    public String getUser(@PathVariable String username, Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (username.equals(userDetails.getUsername())) {
            User user = userService.findUserByUsername(username);
            model.addAttribute("user", user);
            return "user/profile";
        }
        return "redirect:/";
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
        return "user/profile";
    }

    @PostMapping("/user/{username}/delete")
    public String deleteUser(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return "redirect:/logout";
    }

    @GetMapping("/dashboard")
    public String getUserDashboard(Model model, Authentication authentication) {
        User user = getUser(authentication);
        model.addAttribute("user", user);
        return "user/dashboard";
    }

    @GetMapping("/user/projects-list")
    public String getUserProjects(Model model, Authentication authentication) {
        User user = getUser(authentication);
        List<Project> projects = projectService.findAllProjectsByUsername(user.getUsername());
        model.addAttribute("projects", projects);
        model.addAttribute("user", user);
        return "user/projects-list";
    }

    @GetMapping("/user/all-tasks")
    public String getUsersTasks(Model model, Authentication authentication) {
        User user = getUser(authentication);
        List<Task> tasks = taskService.findAllByUserUsername(user.getUsername());
        model.addAttribute("tasks", tasks);
        model.addAttribute("user", user);
        return "user/tasks-list";
    }

    @GetMapping("/users-list")
    public String getUsersList(Model model, Authentication authentication) {
        User user = getUser(authentication);
        List<User> usersList = userService.findAllByUsernameNot(user.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("usersList", usersList);
        return "user/users-list";
    }

    @GetMapping("/register")
    public String registerUserForm(Model model, Authentication authentication) {
        if (authentication != null) {
            return "redirect:/";
        }
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
    public String login(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Authentication authentication) {
        authentication.setAuthenticated(false);
        return "redirect:/";
    }

    @GetMapping("/error")
    public String errorPage() {
        return "error";
    }

    private User getUser(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userService.findUserByUsername(userDetails.getUsername());
    }
}
