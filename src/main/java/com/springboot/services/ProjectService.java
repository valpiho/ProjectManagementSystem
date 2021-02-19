package com.springboot.services;


import com.springboot.entity.Project;

import java.sql.Date;
import java.util.List;

public interface ProjectService {

    Project findProjectByProjectName(String projectName);

    void createProject(String projectName, String description, Date startDate, Date endDate);

    List<Project> findAllProjects();

    void archiveProject(Project project);

    List<Project> findAllArchivedProjects();
    List<Project> findAllOngoingProjects();
}
