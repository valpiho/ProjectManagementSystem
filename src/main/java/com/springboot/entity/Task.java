package com.springboot.entity;

import com.springboot.enumeration.TaskPriority;
import com.springboot.enumeration.ProjectTaskStatus;

import javax.persistence.*;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id", updatable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    private User user;



    private String taskDescription;
    private TaskPriority priority;
    private ProjectTaskStatus status = ProjectTaskStatus.NOT_STARTED;



    public Task() {}

    public Task(String taskDescription, Project project, User user, TaskPriority priority, ProjectTaskStatus status) {
        this.taskDescription = taskDescription;
        this.project = project;
        this.user = user;
        this.priority = priority;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public ProjectTaskStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectTaskStatus projectTaskStatus) {
        this.status = projectTaskStatus;
    }
}
