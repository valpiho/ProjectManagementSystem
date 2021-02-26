package com.springboot.entity;

import com.springboot.enumeration.TaskPriority;
import com.springboot.enumeration.ProjectTaskStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String taskDescription;
    private Date createdAt;
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(length = 6)
    private TaskPriority priority = TaskPriority.HIGH;

    @Enumerated(EnumType.STRING)
    @Column(length = 11)
    private ProjectTaskStatus status;

    public Task() {}

    public Task(String title, String taskDescription, Date createdAt, Date updatedAt, Project project, User user, TaskPriority priority, ProjectTaskStatus status) {
        this.title = title;
        this.taskDescription = taskDescription;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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
