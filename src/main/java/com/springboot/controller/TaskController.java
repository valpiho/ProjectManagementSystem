package com.springboot.controller;

import com.springboot.entity.Task;
import com.springboot.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{task_id}")
    public String getTask(@PathVariable(name = "task_id") Long taskId,
                          Model model) {

        Task task = taskService.findTaskById(taskId);
        model.addAttribute("task", task);

        return "task/task";
    }

    @GetMapping("/all-tasks")
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.findAllTasks();
        model.addAttribute("task", tasks);

        return "task/tasks-list";
    }


    @GetMapping("/{project_id}/tasks-list")
    public String getAllTasksByProject(@PathVariable (name = "project_id") Long id, Model model) {
        List<Task> tasks = taskService.findByProjectId(id);
        model.addAllAttributes(tasks);

        return "task/tasks-list";
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
}
