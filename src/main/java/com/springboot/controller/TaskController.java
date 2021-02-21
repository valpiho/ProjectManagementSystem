package com.springboot.controller;

import com.springboot.entity.Task;
import com.springboot.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/project")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("{user_id}/tasks")
    public String getTasksByUser(@PathVariable (name = "user_id") Long id) {
        taskService.findAllByUserId(id);

        return "";
    }

    @GetMapping("{project_id}/all-tasks")
    public String getAllTasksByProject(@PathVariable (name = "project_id") Long id, Model model) {
        List<Task> tasks = taskService.findByProjectId(id);
        model.addAllAttributes(tasks);

        return "";
    }

    @PostMapping("{project_id}/create-task")
    public String createTask(@PathVariable (name = "project_id") Long id,
                             @ModelAttribute(value = "task") Task task) {
        //TODO: mõtle välja, kuidas määrata, millise projekti koht task käib
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
