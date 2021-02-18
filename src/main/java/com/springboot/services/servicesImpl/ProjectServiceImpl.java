package com.springboot.services.servicesImpl;

import com.springboot.entity.Project;
import com.springboot.repository.ProjectRepository;
import com.springboot.services.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        //TODO: Find Project by projectName
        return null;
    }

    @Override
    public Project createNewProject(String projectName, String description, Date startDate, Date endDate) {
        //TODO: Create New Project
        return null;
    }

    @Override
    public List<Project> findAllProjects() {
        //TODO: findAllProjects
        return null;
    }

}
