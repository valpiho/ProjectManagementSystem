package com.springboot.service;

import com.springboot.entity.Project;
import com.springboot.entity.Task;
import com.springboot.entity.User;
import com.springboot.enumeration.TaskPriority;
import com.springboot.enumeration.ProjectTaskStatus;

public interface TaskService {

    Task findTaskById(long id);

    void createTask(Project project, String description, TaskPriority priority, ProjectTaskStatus status);

    void changeTaskPriority(long id, TaskPriority priority);

    void changeTaskStatus(long id, ProjectTaskStatus status);

    void addMembersToTask(long id, User user);

}
