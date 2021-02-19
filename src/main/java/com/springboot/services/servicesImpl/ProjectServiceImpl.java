package com.springboot.services.servicesImpl;

import com.springboot.entity.Project;
import com.springboot.repository.ProjectRepository;
import com.springboot.services.ProjectService;
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
    public void createProject(String projectName, String description, Date startDate, Date endDate) {
        Project project = new Project();

        project.setProjectName(projectName);
        project.setDescription(description);
        project.setStartDate(startDate);
        project.setEndDate(endDate);

        projectRepository.save(project);
    }

    @Override
    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public void archiveProject(Project project) {
        List<Project> archivedProjects = new ArrayList<>();
        archivedProjects.add(project);
    }

    @Override
    public List<Project> findAllArchivedProjects() {
        //TODO: Find All Archived Projects
        return null;
    }

    @Override
    public List<Project> findAllOngoingProjects() {
        //TODO: Find All Ongoing Projects
        return null;
    }

}
