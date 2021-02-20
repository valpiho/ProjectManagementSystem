package com.springboot.entity;

import com.springboot.enumeration.TaskPriority;
import com.springboot.enumeration.TaskStatus;

import javax.persistence.*;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    //@JoinColumn(name = "id")
    private Project project;

    private String taskDescription;
    private TaskPriority priority;
    private TaskStatus status = TaskStatus.NOT_STARTED;

    @OneToOne
    private User user;

    public Task() {}

    public Task(Long id, String taskDescription, Project project, User user, TaskPriority priority, TaskStatus status) {
        this.id = id;
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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
