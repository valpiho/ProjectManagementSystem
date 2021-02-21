package com.springboot.service;

import com.springboot.entity.Project;
import com.springboot.entity.Task;
import com.springboot.entity.User;
import com.springboot.enumeration.TaskPriority;
import com.springboot.enumeration.ProjectTaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {

    Task findTaskById(long id);
    List<Task> findByProjectId(long id);
    List<Task> findAllByUserId(long id);
    Task findByIdAndProjectId(Long taskId, Long projectId);

    void createTask(Project project, String description, TaskPriority priority, ProjectTaskStatus status);

    void changeTaskPriority(long id, TaskPriority priority);

    void changeTaskStatus(long id, ProjectTaskStatus status);

    void addMembersToTask(long id, User user);

    void deleteTask(long id);

}
