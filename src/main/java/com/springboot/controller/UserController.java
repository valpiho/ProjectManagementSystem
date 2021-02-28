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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

import static com.springboot.enumeration.ProjectTaskStatus.*;

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
            model.addAttribute("authUser", getAuthUser(authentication));
        }
        return "index";
    }

    @GetMapping("/user")
    public String getAuthUser(Model model, Authentication authentication) {
        User user = userService.findUserByUsername(getAuthUser(authentication).getUsername());
        model.addAttribute("authUser", getAuthUser(authentication));
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/user/{username}")
    public String getAuthUser(@PathVariable String username, Model model, Authentication authentication) {
        User user = userService.findUserByUsername(username);
        model.addAttribute("authUser", getAuthUser(authentication));
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/user/{username}/update")
    public String updateUser(@PathVariable String username,
                             Model model, Authentication authentication) {
        User user = userService.findUserByUsername(username);
        model.addAttribute("authUser", getAuthUser(authentication));
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("/user/{username}/updateForm")
    public String updateUser(@PathVariable(value = "username") String username,
                             @ModelAttribute(value = "user") User user, Model model, Authentication authentication) throws UsernameExistException, EmailExistException {
        if(userService.findUserByUsername(user.getUsername()) != null && userService.findUserByUsername(user.getUsername()).getUsername().equals(user.getUsername())) {
            model.addAttribute("authUser", getAuthUser(authentication));
            model.addAttribute("user", user);
            model.addAttribute("errorUser", "User exists");
            return "user/update";
        }
        if(userService.findUserByEmail(user.getEmail()) != null && userService.findUserByEmail(user.getEmail()).getEmail().equals(user.getEmail())) {
            model.addAttribute("authUser", getAuthUser(authentication));
            model.addAttribute("user", user);
            model.addAttribute("errorEmail", "Email exists");
            return "user/update";
        }
        userService.updateUserByUsername(username, user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail(), user.getPassword());
        return "user/profile";
    }

    @PostMapping("/user/{username}/delete")
    // TODO: Who can do it???
    public String deleteUser(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return "redirect:/logout";
    }

    @GetMapping("/dashboard")
    public String getUserDashboard(Model model, Authentication authentication) {
        List<Project> projects = projectService.findAllProjects();
        List<Project> onGoingProjects = projectService.findAllProjectsByStatus(IN_PROGRESS);
        List<Project> completedProjects = projectService.findAllProjectsByStatus(COMPLETED);
        List<Project> archivedProjects = projectService.findAllProjectsByStatus(ARCHIVED);
        List<Task> tasks = taskService.findAllTasks();
        List<Task> onGoingTasks = taskService.findAllByStatus(IN_PROGRESS);
        List<Task> completedTasks = taskService.findAllByStatus(COMPLETED);
        List<Task> archivedTasks = taskService.findAllByStatus(ARCHIVED);
        model.addAttribute("authUser", getAuthUser(authentication));
        model.addAttribute("projects", projects);
        model.addAttribute("onGoingProjects", onGoingProjects);
        model.addAttribute("completedProjects", completedProjects);
        model.addAttribute("archivedProjects", archivedProjects);
        model.addAttribute("tasks", tasks);
        model.addAttribute("onGoingTasks", onGoingTasks);
        model.addAttribute("completedTasks", completedTasks);
        model.addAttribute("archivedTasks", archivedTasks);

        return "user/dashboard";
    }

    @GetMapping("/user/projects-list")
    public String getUserProjects(Model model, Authentication authentication) {
        List<Project> projects = projectService.findAllProjectsByUsername(getAuthUser(authentication).getUsername());
        model.addAttribute("projects", projects);
        model.addAttribute("authUser", getAuthUser(authentication));
        return "user/projects-list";
    }

    @GetMapping("/user/tasks-list")
    public String getUsersTasks(Model model, Authentication authentication) {
        List<Task> tasks = taskService.findAllByUserUsername(getAuthUser(authentication).getUsername());
        model.addAttribute("tasks", tasks);
        model.addAttribute("authUser", getAuthUser(authentication));
        return "user/tasks-list";
    }

    @GetMapping("/users-list")
    public String getUsersList(Model model, Authentication authentication) {
        List<User> usersList = userService.findAllByUsernameNot(getAuthUser(authentication).getUsername());
        model.addAttribute("authUser", getAuthUser(authentication));
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
    public String registerUser(@ModelAttribute(value = "user") User user, Model model)
            throws UsernameExistException, EmailExistException {
        if(userService.findUserByUsername(user.getUsername()) != null && userService.findUserByUsername(user.getUsername()).getUsername().equals(user.getUsername())) {
            model.addAttribute("errorUser", "User exists");
            return "register";
        }
        if(userService.findUserByEmail(user.getEmail()) != null && userService.findUserByEmail(user.getEmail()).getEmail().equals(user.getEmail())) {
            model.addAttribute("errorEmail", "Email exists");
            return "register";
        }
        userService.registerNewUser(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail(), user.getPassword());
        return "redirect:/login-success";
    }

    @GetMapping("/login")
    public String login(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/";
        }

        return "login";
    }

    @GetMapping("/login-success")
    public String loginSuccess(Authentication authentication, Model model) {
        if (authentication != null) {
            return "redirect:/";
        }
        model.addAttribute("success", "User has been registered");
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

    private User getAuthUser(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userService.findUserByUsername(userDetails.getUsername());
    }
}
