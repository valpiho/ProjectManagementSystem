package com.springboot.service;

import com.springboot.entity.Project;
import com.springboot.entity.Task;
import com.springboot.entity.User;
import com.springboot.enumeration.TaskPriority;
import com.springboot.enumeration.ProjectTaskStatus;

import java.util.List;

public interface TaskService {

    Task findTaskById(long id);
    List<Task> findByProjectId(long id);
    List<Task> findAllByUserUsername(String username);
    List<Task> findAllByStatus(ProjectTaskStatus status);

    void createTask(Long projectId, String description, TaskPriority priority, String username, ProjectTaskStatus status);

    void updateTask(long taskId, String title, String description, TaskPriority priority, User user, ProjectTaskStatus status);

    void changeTaskPriority(long id, TaskPriority priority);

    void changeTaskStatus(long id, ProjectTaskStatus status);

    void addMembersToTask(long id, User user);

    void deleteTask(long id);

    List<Task> findAllTasks();

    //TODO: AT tests
    void addTask(Long id, String taskDescription, TaskPriority priority, ProjectTaskStatus status);
}
