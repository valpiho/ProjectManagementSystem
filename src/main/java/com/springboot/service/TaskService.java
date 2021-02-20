package com.springboot.service;

import com.springboot.entity.Project;
import com.springboot.entity.Task;
import com.springboot.entity.User;
import com.springboot.enumeration.TaskPriority;
import com.springboot.enumeration.ProjectTaskStatus;

import java.util.Optional;

public interface TaskService {

    Optional<Task> findTaskById(Long id);

    void createTask(Project project, String description, TaskPriority priority, ProjectTaskStatus projectTaskStatus);

    void changeTaskPriority(Long id, TaskPriority priority);

    void changeTaskStatus(Long id, ProjectTaskStatus projectTaskStatus);

    void addMembersToProject(Long id, User user);

}
