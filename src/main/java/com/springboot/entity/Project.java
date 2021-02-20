package com.springboot.entity;

import com.springboot.enumeration.ProjectTaskStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectName;
    private String description;
    private Date startDate;
    private Date endDate;

    //@ManyToOne
    private ProjectTaskStatus status = ProjectTaskStatus.NOT_STARTED;
    private boolean archivedProject = false;

    @ManyToMany(mappedBy = "projects")
    private Set<User> users;

    @ManyToOne
    @JoinColumn(name = "task_id", updatable = false)
    private Task tasks;

    public Project() { }

    public Project(String projectName, String description, Date startDate, Date endDate, ProjectTaskStatus status) {
        this.projectName = projectName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
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

    public ProjectTaskStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectTaskStatus projectTaskStatus) {
        this.status = projectTaskStatus;
    }

    public boolean isArchivedProject() {
        return archivedProject;
    }

    public void setArchivedProject(boolean archivedProject) {
        this.archivedProject = archivedProject;
    }
}
