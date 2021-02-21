package com.springboot.entity;

import com.springboot.enumeration.ProjectTaskStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectName;
    private String description;
    private Date startDate;
    private Date endDate;
    private Date createdAt;
    private Date updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(length = 11)
    private ProjectTaskStatus status = ProjectTaskStatus.NOT_STARTED;

    @ManyToMany(mappedBy = "projects", fetch = FetchType.LAZY)
    private List<User> users;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Task> tasks;

    public Project() { }

    public Project(String projectName, String description, Date startDate, Date endDate, Date createdAt, Date updatedAt, ProjectTaskStatus status) {
        this.projectName = projectName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.users = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public ProjectTaskStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectTaskStatus projectTaskStatus) {
        this.status = projectTaskStatus;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
