package com.springboot.controller;

import com.springboot.entity.Task;
import com.springboot.entity.User;
import com.springboot.enumeration.ProjectTaskStatus;
import com.springboot.service.TaskService;
import com.springboot.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService,
                          UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/{task_id}")
    public String getTask(@PathVariable(name = "task_id") Long taskId,
                          Model model) {

        Task task = taskService.findTaskById(taskId);
        model.addAttribute("task", task);

        return "task/task";
    }

    @GetMapping("/tasks-list")
    public String getAllTasks(Model model, Authentication authentication) {
        User user = getUser(authentication);
        List<Task> tasks = taskService.findAllTasks();
        model.addAttribute("user", user);
        model.addAttribute("tasks", tasks);
        return "task/tasks-list";
    }


    @GetMapping("/{project_id}/tasks-list")
    public String getAllTasksByProject(@PathVariable (name = "project_id") Long id, Model model) {
        List<Task> tasks = taskService.findByProjectId(id);
        model.addAttribute("tasks", tasks);

        return "task/tasks-list";
    }

    @GetMapping("/not-started")
    public String getAllNotStartedTasks(Model model) {
        List<Task> tasks = taskService.findAllByStatus(ProjectTaskStatus.NOT_STARTED);
        model.addAttribute("tasks", tasks);
        return "task/tasks-not-started";
    }

    @GetMapping("/in-progress")
    public String getAllTasksInProgress(Model model) {
        List<Task> tasks = taskService.findAllByStatus(ProjectTaskStatus.IN_PROGRESS);
        model.addAttribute("tasks", tasks);
        return "task/tasks-inprogress";
    }

    @GetMapping ("/completed")
    public String getAllCompletedTasks(Model model) {
        List<Task> tasks = taskService.findAllByStatus(ProjectTaskStatus.COMPLETED);
        model.addAttribute("tasks", tasks);
        return "task/tasks-completed";
    }

    @GetMapping ("/archived")
    public String getAllArchivedTasks(Model model) {
        List<Task> tasks = taskService.findAllByStatus(ProjectTaskStatus.ARCHIVED);
        model.addAttribute("tasks", tasks);
        return "task/tasks-archived";
    }

    @GetMapping("/create")
    public String createTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "task/create-task";
    }

    @PostMapping("/create-task")
    public String createTask(@ModelAttribute(value = "task") Task task) {

        taskService.createTask(task.getProject(),
                                task.getTaskDescription(),
                                task.getPriority(),
                                task.getStatus());
        return "task/task";
    }

    @GetMapping("{task_id}/update")
    public String updateTask(@PathVariable(name = "task_id") Long taskId,
                             Model model){
        Task task = taskService.findTaskById(taskId);
        model.addAttribute("task", task);
        return "task/update";
    }

    @PostMapping("/{task_id}/updateForm")
    public String updateTask(@PathVariable(name = "task_id") Long taskId,
                             @ModelAttribute(value = "task") Task task) {

        taskService.updateTask(taskId,
                task.getTitle(),
                task.getTaskDescription(),
                task.getPriority(),
                task.getUser(),
                task.getStatus());

        return "task/task";
    }

    @DeleteMapping("tasks/{task_id}/delete-task")
    public String deleteTask(@PathVariable (name = "task_id") Long id) {
        taskService.deleteTask(id);

        return "";
    }

    private User getUser(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userService.findUserByUsername(userDetails.getUsername());
    }
}
