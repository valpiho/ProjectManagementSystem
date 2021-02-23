package com.springboot.controller;

import com.springboot.entity.Task;
import com.springboot.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("{project_id}/tasks/{task_id}")
    public String getTask(@PathVariable(name = "project_id") Long projectId,
                          @PathVariable(name = "task_id") Long taskId,
                          Model model) {

        Task task = taskService.findByIdAndProjectId(taskId, projectId);
        model.addAttribute("task", task);

        return "task";
    }

    @GetMapping("{user_id}/tasks")
    public String getTasksByUser(@PathVariable (name = "user_id") Long userId) {
        taskService.findAllByUserId(userId);

        return "";
    }

    @GetMapping("{project_id}/tasks/all-tasks")
    public String getAllTasksByProject(@PathVariable (name = "project_id") Long id, Model model) {
        List<Task> tasks = taskService.findByProjectId(id);
        model.addAllAttributes(tasks);

        return "";
    }

    @GetMapping("/tasks")
    public String createTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "tasks-add";
    }

    @PostMapping("/tasks/create-task")
    public String createTask(@ModelAttribute(value = "task") Task task) {

        taskService.createTask(task.getProject(),
                                task.getTaskDescription(),
                                task.getPriority(),
                                task.getStatus());
        return "task";
    }

    @GetMapping("/{project_id}/tasks/{task_id}/update")
    public String updateTask(@PathVariable(name = "project_id") Long projectId,
                             @PathVariable(name = "task_id") Long taskId,
                             Model model){
        Task task = taskService.findByIdAndProjectId(taskId, projectId);
        model.addAttribute("task", task);
        return "task/update";
    }

    @PostMapping("/{project_id}/tasks/{task_id}/updateForm")
    public String updateTask(@PathVariable(name = "project_id") Long projectId,
                             @PathVariable(name = "task_id") Long taskId,
                             @ModelAttribute(value = "task") Task task) {

        taskService.updateTask(projectId, taskId,
                task.getTaskDescription(),
                task.getPriority(),
                task.getStatus());

        return "task";
    }

    @DeleteMapping("tasks/{task_id}/delete-task")
    public String deleteTask(@PathVariable (name = "task_id") Long id) {
        taskService.deleteTask(id);

        return "";
    }
}
