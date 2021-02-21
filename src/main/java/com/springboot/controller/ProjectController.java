package com.springboot.controller;

import com.springboot.entity.Project;
import com.springboot.enumeration.ProjectTaskStatus;
import com.springboot.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{project_id}")
    public String getProject(@PathVariable (name = "project_id") Long projectId, Model model) {
        Project project = projectService.findProjectById(projectId);
        model.addAttribute("project", project);
        return "";
    }

    @GetMapping("/all-projects")
    public String getAllProjects() {
        projectService.findAllProjects();
        return "";
    }

    //TODO: kuidas otsida ühe kasutaja kõiki projekte?
   /* @GetMapping("{user_id}")
    public String getAllProjectsByUser(@PathVariable (name = "user_id") Long userId) {
        projectService.findAllByUsers(userId);
        return "";
    }*/

    @GetMapping("/archived-projects")
    public String getAllArchivedProjects() {
        projectService.findAllArchivedProjects(ProjectTaskStatus.ARCHIVED);
        return "";
    }

    @GetMapping("/ongoing-projects")
    public String getAllOngoingProjects() {
        projectService.findAllOngoingProjects(ProjectTaskStatus.IN_PROGRESS);
        return "";
    }

    @GetMapping("/create")
    public String createProjectForm(Model model) {
        model.addAttribute("project", new Project());
        return "";
    }

    @PostMapping("/create/new-project")
    public String createProject(@ModelAttribute(value = "project") Project project) {

        projectService.createProject(project.getProjectName(),
                                        project.getDescription(),
                                        project.getStartDate(),
                                        project.getEndDate(),
                                        project.getStatus());
        return "";
    }

    @GetMapping("/{project_id}/update")
    public String updateProject(@PathVariable (name = "project_id") Long projectId, Model model) {
        Project project = projectService.findProjectById(projectId);
        model.addAttribute("project", project);
        return "";
    }

    @PostMapping("/{project_id}/updateForm")
    public String updateProject(@PathVariable (name = "project_id") Long projectId,
                                @ModelAttribute(value = "project") Project project) {

        projectService.updateProject(projectId,
                project.getProjectName(),
                project.getDescription(),
                project.getStartDate(),
                project.getEndDate(),
                project.getStatus());

        return "";
    }


}
