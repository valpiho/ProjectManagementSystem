package com.springboot.controller;

import com.springboot.entity.Project;
import com.springboot.entity.Task;
import com.springboot.repository.ProjectRepository;
import com.springboot.service.ProjectService;
import com.springboot.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/project")
public class TaskController {

    private final TaskService taskService;
    private ProjectService projectService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("{project_id}/{task_id}")
    public String getTask(@PathVariable(name = "project_id") Long projectId,
                          @PathVariable(name = "task_id") Long taskId,
                          Model model) {

        Task task = taskService.findByIdAndProjectId(taskId, projectId);
        model.addAttribute("task", task);

        return "";
    }

    @GetMapping("{user_id}/tasks")
    public String getTasksByUser(@PathVariable (name = "user_id") Long userId) {
        taskService.findAllByUserId(userId);

        return "";
    }

    @GetMapping("{project_id}/all-tasks")
    public String getAllTasksByProject(@PathVariable (name = "project_id") Long id, Model model) {
        List<Task> tasks = taskService.findByProjectId(id);
        model.addAllAttributes(tasks);

        return "";
    }

    @GetMapping("")
    public String createTaskForm(Model model) {
        model.addAttribute("task", new Task());
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

    @DeleteMapping("{task_id}/delete-task")
    public String deleteTask(@PathVariable (name = "task_id") Long id) {
        taskService.deleteTask(id);

        return "";
    }

}
