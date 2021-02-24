package com.springboot.service.serviceImpl;

import com.springboot.entity.Project;
import com.springboot.entity.Task;
import com.springboot.entity.User;
import com.springboot.enumeration.TaskPriority;
import com.springboot.enumeration.ProjectTaskStatus;
import com.springboot.repository.TaskRepository;
import com.springboot.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task findTaskById(long id) {
        return taskRepository.findById(id);
    }

    @Override
    public List<Task> findByProjectId(long id) {
        return taskRepository.findByProjectId(id);
    }

    @Override
    public List<Task> findAllByUsername(String username) {
        return taskRepository.findAllByUsername(username);
    }

    @Override
    public void createTask(Project project, String description, TaskPriority priority, ProjectTaskStatus status) {
        Task task = new Task();

        task.setProject(project);
        task.setTaskDescription(description);
        task.setPriority(TaskPriority.MEDIUM);
        task.setStatus(ProjectTaskStatus.NOT_STARTED);

        taskRepository.save(task);
    }

    @Override
    public void updateTask(long taskId, String title, String description, TaskPriority priority, User user, ProjectTaskStatus status) {
        Task task = taskRepository.findById(taskId);

        task.setTitle(title);
        task.setTaskDescription(description);
        task.setPriority(priority);
        task.setUser(user);
        task.setStatus(status);

        taskRepository.save(task);
    }


    @Override
    public void changeTaskPriority(long id, TaskPriority priority) {
       Task task = taskRepository.findById(id);
       task.setPriority(priority);
       taskRepository.save(task);
    }

    @Override
    public void changeTaskStatus(long id, ProjectTaskStatus status) {
        Task task = taskRepository.findById(id);
        task.setStatus(status);
        taskRepository.save(task);
    }

    @Override
    public void addMembersToTask(long id, User user) {
        Task task = taskRepository.findById(id);
        task.setUser(user);
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(long id) {
        Task task = taskRepository.findById(id);
        taskRepository.delete(task);
    }

    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }
}
