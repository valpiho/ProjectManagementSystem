package com.springboot.controller;

import com.springboot.entity.Project;
import com.springboot.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({"/", "project"})
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects/{projectName}")
    public String getProject(@PathVariable String projectName, Model model) {
        Project project = this.projectService.findProjectByProjectName(projectName);
        model.addAttribute("project", project);
        return "project";

        //TODO: Should we use findProjectByProjectId here?
    }

    @GetMapping("/projects/all-projects")
    public String getAllProjects() {
        projectService.findAllProjects();
        return "";
    }

    @PostMapping("/create")
    public String createProject(@ModelAttribute(value = "project") Project project) {

        projectService.createProject(project.getProjectName(),
                                        project.getDescription(),
                                        project.getStartDate(),
                                        project.getEndDate(),
                                        project.getStatus());

        return "";
    }

}
