package com.springboot.services;


import com.springboot.entity.Project;

import java.util.Date;
import java.util.List;

public interface ProjectService {

    Project findProjectByProjectName(String projectName);

    Project createProject(Project project);

    List<Project> findAllProjects();

    void archiveProject(Project project);

    List<Project> findAllArchivedProjects();
    List<Project> findAllOngoingProjects();
}
