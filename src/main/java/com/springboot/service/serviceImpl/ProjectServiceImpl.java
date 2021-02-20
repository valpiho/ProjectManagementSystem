package com.springboot.service.serviceImpl;

import com.springboot.entity.Project;
import com.springboot.enumeration.ProjectTaskStatus;
import com.springboot.repository.ProjectRepository;
import com.springboot.service.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    @Override
    public Project findProjectByProjectName(String projectName) {
        return projectRepository.findProjectByProjectName(projectName);
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
    public void archiveProject(Project project) {
        project.setArchivedProject(true);
        System.out.println("The project is archived.");
    }

    /*@Override
    public Project findAllArchivedProjects() {
        return projectRepository.findAllByArchivedProjectIsTrue(true);
    }*/

    @Override
    public List<Project> findAllOngoingProjects() {
        //TODO: Find All Ongoing Projects
        return null;
    }

}
