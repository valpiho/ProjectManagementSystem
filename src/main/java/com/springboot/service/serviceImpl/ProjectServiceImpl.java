package com.springboot.service.serviceImpl;

import com.springboot.entity.Project;
import com.springboot.entity.User;
import com.springboot.enumeration.ProjectTaskStatus;
import com.springboot.repository.ProjectRepository;
import com.springboot.service.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
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
    public void createProject(String projectName, String description, Date startDate, Date endDate, ProjectTaskStatus status) {
        Project project = new Project();

        project.setProjectName(projectName);
        project.setDescription(description);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        project.setStatus(status);

        projectRepository.save(project);
    }

    @Override
    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public List<Project> findProjectsByUsers(User user) {
        return projectRepository.findProjectsByUsers(user);
    }

    @Override
    public List<Project> findAllArchivedProjects(ProjectTaskStatus status) {
        return projectRepository.findAllByStatus(ProjectTaskStatus.ARCHIVED);
    }

    @Override
    public List<Project> findAllOngoingProjects(ProjectTaskStatus status) {
        return projectRepository.findAllByStatus(ProjectTaskStatus.IN_PROGRESS);
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

}
