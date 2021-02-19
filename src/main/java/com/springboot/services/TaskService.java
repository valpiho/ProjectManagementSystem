package com.springboot.services;

import com.springboot.entity.Project;
import com.springboot.entity.Task;
import com.springboot.entity.User;
import com.springboot.enumeration.TaskPriority;
import com.springboot.enumeration.TaskStatus;

import java.util.Optional;

public interface TaskService {

    Optional<Task> findTaskById(Long id);

    void createTask(Project project, String description, TaskPriority priority, TaskStatus status);

    void changeTaskPriority(Long id, TaskPriority priority);

    void changeTaskStatus(Long id, TaskStatus status);

    void addMembersToProject(Long id, User user);

}
