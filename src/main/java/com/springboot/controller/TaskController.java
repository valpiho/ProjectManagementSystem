package com.springboot.controller;

import com.springboot.entity.Task;
import com.springboot.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project/{id}")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks/{id}")
    public String getAllUsersTasks(@PathVariable Long id) {
        this.taskService.findAllByUserId(id);

        return "";
    }

    @GetMapping("/all-tasks")
    public String getAllTasksByProjectId(@PathVariable Long id) {
        this.taskService.findByProjectId(id);

        return "";
    }

    @PostMapping("/create-task")
    public String createTask(@ModelAttribute(value = "task") Task task) {

        taskService.createTask(task.getProject(),
                                task.getTaskDescription(),
                                task.getPriority(),
                                task.getStatus());
        return "";
    }

    @DeleteMapping("/delete-task/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);

        return "";
    }

}
