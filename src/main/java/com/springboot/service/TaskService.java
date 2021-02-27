package com.springboot.service;

import com.springboot.entity.Project;
import com.springboot.entity.Task;
import com.springboot.entity.User;
import com.springboot.enumeration.TaskPriority;
import com.springboot.enumeration.ProjectTaskStatus;

import java.util.List;

public interface TaskService {

    Task findTaskById(Long id);

    List<Task> findByProjectId(Long id);

    List<Task> findAllByUserUsername(String username);

    List<Task> findAllByStatus(ProjectTaskStatus status);

    void createTask(Long projectId, String title, String description, TaskPriority priority, String username, ProjectTaskStatus status);

    void updateTask(Long taskId, String title, String description, TaskPriority priority, User user, ProjectTaskStatus status);

    void changeTaskPriority(Long id, TaskPriority priority);

    void changeTaskStatus(Long id, ProjectTaskStatus status);

    void addMembersToTask(Long id, User user);

    void deleteTask(Long id);

    List<Task> findAllTasks();
}
