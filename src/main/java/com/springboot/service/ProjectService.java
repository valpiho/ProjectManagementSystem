package com.springboot.service;


import com.springboot.entity.Project;
import com.springboot.entity.User;
import com.springboot.enumeration.ProjectTaskStatus;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ProjectService {

    Project findProjectByProjectName(String projectName);
    Project findProjectById(Long id);

    Project createProject(String projectName, String description, Date startDate, Date endDate, ProjectTaskStatus status);

    List<Project> findAllProjects();

    List<Project> findAllArchivedProjects(ProjectTaskStatus status);
    List<Project> findAllOngoingProjects(ProjectTaskStatus status);

    void updateProjectNameAndDescription(Long id, String projectName, String description);

    void changeProjectStatus(Long id, ProjectTaskStatus status);

    void addUserToProject(Long id, Set<User> users);
}
