package com.springboot.service.serviceImpl;

import com.springboot.entity.Project;
import com.springboot.entity.User;
import com.springboot.enumeration.ProjectTaskStatus;
import com.springboot.repository.ProjectRepository;
import com.springboot.service.ProjectService;
import com.springboot.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserService userService;

    public ProjectServiceImpl(ProjectRepository projectRepository,
                              UserService userService) {
        this.projectRepository = projectRepository;
        this.userService = userService;
    }

    @Override
    public List<Project> findAllProjectsByUsername(String username) {
        return projectRepository.findProjectByOwner_Username(username);
    }

    @Override
    public Project findProjectByProjectName(String projectName) {
        return projectRepository.findProjectByProjectName(projectName);
    }

    @Override
    public Project findProjectById(Long id) {
        return projectRepository.findProjectById(id);
    }

    @Override
    public void createProject(String username, String projectName, String description, Date startDate, Date endDate) {
        Project project = new Project();
        User user = userService.findUserByUsername(username);
        project.setOwner(user);
        project.setProjectName(projectName);
        project.setDescription(description);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        project.setCreatedAt(new Date());
        project.setStatus(ProjectTaskStatus.NOT_STARTED);
        projectRepository.save(project);
    }

    @Override
    public List<Project> findAllProjectsByStatus(ProjectTaskStatus status) {
        return projectRepository.findAllByStatus(status);
    }

    @Override
    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public void updateProject(Long id, String projectName, String description, Date startDate, Date endDate, ProjectTaskStatus status) {
        Project project = projectRepository.findProjectById(id);
        project.setProjectName(projectName);
        project.setDescription(description);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        project.setStatus(status);
    }

    @Override
    public void changeProjectStatus(Long id, ProjectTaskStatus status) {
        Project project = projectRepository.findProjectById(id);
        project.setStatus(status);
        projectRepository.save(project);
    }

    @Override
    public void addUserToProject(Long id, List<User> users) {
        Project project = projectRepository.findProjectById(id);
        project.setUsers(users);
        projectRepository.save(project);
    }

    @Override
    public void deleteProject(Long id) {

    }

}
