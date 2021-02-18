package com.springboot.services;


import com.springboot.entity.Project;

import java.util.Date;
import java.util.List;

public interface ProjectService {

    Project findProjectByProjectName(String projectName);

    Project createNewProject(String projectName, String description, Date startDate, Date endDate);

    List<Project> findAllProjects();
}
