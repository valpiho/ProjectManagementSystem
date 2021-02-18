package com.springboot.services.servicesImpl;

import com.springboot.entity.Project;
import com.springboot.repository.ProjectRepository;
import com.springboot.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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
    public Project createProject(Project project) {
        //TODO: Create New Project
        return projectRepository.save(project);
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
