package com.springboot.controller;

import com.springboot.entity.Project;
import com.springboot.entity.Task;
import com.springboot.entity.User;
import com.springboot.enumeration.ProjectTaskStatus;
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
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;
    private final ProjectService projectService;

    public TaskController(TaskService taskService,
                          UserService userService,
                          ProjectService projectService) {
        this.taskService = taskService;
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping("tasks/{task_id}")
    public String getTask(@PathVariable(name = "task_id") Long taskId,
                          Model model, Authentication authentication) {
        Task task = taskService.findTaskById(taskId);
        model.addAttribute("authUser", getAuthUser(authentication));
        model.addAttribute("task", task);
        return "task/task";
    }

    @GetMapping("/tasks-list")
    public String getAllTasks(Model model, Authentication authentication) {
        List<Task> tasks = taskService.findAllTasks();
        model.addAttribute("authUser", getAuthUser(authentication));
        model.addAttribute("tasks", tasks);
        return "task/tasks-list";
    }


    @GetMapping("/{project_id}/tasks-list")
    public String getAllTasksByProject(@PathVariable(name = "project_id") Long id, Model model) {
        List<Task> tasks = taskService.findByProjectId(id);
        Project project = projectService.findProjectById(id);
        model.addAttribute("project", project);
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

    @GetMapping("/completed")
    public String getAllCompletedTasks(Model model) {
        List<Task> tasks = taskService.findAllByStatus(ProjectTaskStatus.COMPLETED);
        model.addAttribute("tasks", tasks);
        return "task/tasks-completed";
    }

    @GetMapping("/archived")
    public String getAllArchivedTasks(Model model) {
        List<Task> tasks = taskService.findAllByStatus(ProjectTaskStatus.ARCHIVED);
        model.addAttribute("tasks", tasks);
        return "task/tasks-archived";
    }

    //TODO AT: added GetMapping for creating a task under Project
    @GetMapping("/create")
    public String createTask(@RequestParam(value = "project_id", required = false) Long project_id,
                             Model model, Authentication authentication) {
        model.addAttribute("authUser", getAuthUser(authentication));
        model.addAttribute("task", new Task());
        if (project_id != null) {
            Project project = projectService.findProjectById(project_id);
            model.addAttribute("project", project);
            return "task/create";
        } else {
            List<Project> projects = projectService.findAllProjectsByUsername(getAuthUser(authentication).getUsername());
            model.addAttribute("projects", projects);
        }
        return "task/create-task";
    }

    //TODO AT: added PostMapping for task under Project
    @PostMapping("/task-create")
    public String createTask(@RequestParam(value = "project_id", required = false) Long project_id,
                             @ModelAttribute(value = "task") Task task, Authentication authentication) {
        if (project_id != null) {
            taskService.createTask(project_id, task.getTitle(), task.getTaskDescription(), task.getPriority(), getAuthUser(authentication).getUsername(), task.getStatus());
            return String.format("redirect:/projects/%s", project_id);
        }
        taskService.createTask(task.getProject().getId(), task.getTitle(), task.getTaskDescription(), task.getPriority(), getAuthUser(authentication).getUsername(), task.getStatus());
        return String.format("redirect:/projects/%s", project_id);
    }


    @GetMapping("{task_id}/update")
    public String updateTask(@PathVariable(name = "task_id") Long taskId,
                             Model model, Authentication authentication) {
        Task task = taskService.findTaskById(taskId);
        model.addAttribute("task", task);
        model.addAttribute("authUser", getAuthUser(authentication));
        return "task/update";
    }

    @PostMapping("/{task_id}/updateForm")
    public String updateTask(@PathVariable(name = "task_id") Long taskId,
                             @ModelAttribute(value = "user") Task task) {

        taskService.updateTask(taskId,
                task.getTitle(),
                task.getTaskDescription(),
                task.getPriority(),
                task.getUser(),
                task.getStatus());

        return String.format("redirect:/tasks/%s", task.getId());
    }

    @DeleteMapping("tasks/{task_id}/delete-task")
    public String deleteTask(@PathVariable(name = "task_id") Long id) {
        taskService.deleteTask(id);

        return "";
    }

    private User getAuthUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userService.findUserByUsername(userDetails.getUsername());
    }
}
