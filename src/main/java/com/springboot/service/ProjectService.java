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

    void createProject(String username, String projectName, String description, Date startDate, Date endDate, ProjectTaskStatus status);

    List<Project> findAllProjectsByUsername(String username);

    List<Project> findAllArchivedProjects(ProjectTaskStatus status);

    List<Project> findAllOngoingProjects(ProjectTaskStatus status);

    List<Project> findAllProjects();

    void updateProject(Long id, String projectName, String description, Date startDate, Date endDate, ProjectTaskStatus status);

    void changeProjectStatus(Long id, ProjectTaskStatus status);

    void addUserToProject(Long id, List<User> users);

    void deleteProject(Long id);
}
