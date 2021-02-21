package com.springboot.controller;

import com.springboot.entity.Task;
import com.springboot.service.ProjectService;
import com.springboot.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/project/{id}")
public class TaskController {

    private TaskService taskService;

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
}
