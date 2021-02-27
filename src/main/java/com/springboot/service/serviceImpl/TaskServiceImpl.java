package com.springboot.service.serviceImpl;

import com.springboot.entity.Project;
import com.springboot.entity.Task;
import com.springboot.entity.User;
import com.springboot.enumeration.TaskPriority;
import com.springboot.enumeration.ProjectTaskStatus;
import com.springboot.repository.TaskRepository;
import com.springboot.service.ProjectService;
import com.springboot.service.TaskService;
import com.springboot.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;
    private final ProjectService projectService;


    public TaskServiceImpl(TaskRepository taskRepository,
                           UserService userService,
                           ProjectService projectService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
        this.projectService = projectService;
    }

    @Override
    public Task findTaskById(Long id) {
        return taskRepository.findTaskById(id);
    }

    @Override
    public List<Task> findByProjectId(Long id) {
        return taskRepository.findByProjectId(id);
    }

    @Override
    public List<Task> findAllByUserUsername(String username) {
        return taskRepository.findAllByUserUsername(username);
    }

    @Override
    public List<Task> findAllByStatus(ProjectTaskStatus status) {
        return taskRepository.findAllByStatus(status);
    }

    @Override
    public void createTask(Long projectId, String title, String description, TaskPriority priority, String username, ProjectTaskStatus status) {
        Task task = new Task();
        User user = userService.findUserByUsername(username);
        Project project = projectService.findProjectById(projectId);

        task.setTitle(title);
        task.setProject(project);
        task.setUser(user);
        task.setTaskDescription(description);
        task.setPriority(priority);
        task.setStatus(ProjectTaskStatus.NOT_STARTED);
        task.setCreatedAt(new Date());
        taskRepository.save(task);
    }

    @Override
    public void updateTask(Long taskId, String title, String description, TaskPriority priority, ProjectTaskStatus status) {
        Task task = taskRepository.findTaskById(taskId);

        task.setTitle(title);
        task.setTaskDescription(description);
        task.setPriority(priority);
        task.setUpdatedAt(new Date());
        task.setStatus(status);
        taskRepository.save(task);
    }

    @Override
    public void addMembersToTask(Long id, User user) {
        Task task = taskRepository.findTaskById(id);
        task.setUser(user);
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findTaskById(id);
        taskRepository.delete(task);
    }

    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }
}
