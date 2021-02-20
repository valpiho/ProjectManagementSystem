package com.springboot.service;


import com.springboot.entity.Project;
import com.springboot.enumeration.ProjectTaskStatus;

import java.sql.Date;
import java.util.List;

public interface ProjectService {

    Project findProjectByProjectName(String projectName);

    void createProject(String projectName, String description, Date startDate, Date endDate, ProjectTaskStatus status);

    List<Project> findAllProjects();

    void archiveProject(Project project);

    //Project findAllArchivedProjects();
    List<Project> findAllOngoingProjects();
}
