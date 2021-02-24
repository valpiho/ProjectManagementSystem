package com.springboot.controller;

import com.springboot.entity.Project;
import com.springboot.entity.User;
import com.springboot.enumeration.ProjectTaskStatus;
import com.springboot.service.ProjectService;
import com.springboot.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{project_id}")
    public String getProject(@PathVariable (name = "project_id") Long projectId,
                             Model model,
                             Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Project project = projectService.findProjectById(projectId);
        model.addAttribute("project", project);
        model.addAttribute("user", userDetails);
        return "project/project";
    }

    @GetMapping("/projects-list")
    public String getAllProjects(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<Project> projects = projectService.findAllProjects();
        model.addAttribute("projects", projects);
        model.addAttribute("user", userDetails);
        return "project/projects-list";
    }

    @GetMapping("/projects-list-archived")
    public String getAllArchivedProjects(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        // TODO: Get archived projects by authorized user
        List<Project> projects = projectService.findAllArchivedProjects(ProjectTaskStatus.ARCHIVED);
        model.addAttribute("projects", projects);
        model.addAttribute("user", userDetails);
        return "project/projects-list-archived";
    }

    @GetMapping("/projects-list-ongoing")
    public String getAllOngoingProjects(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        // TODO: Get ongoing projects by authorized user
        List<Project> projects = projectService.findAllOngoingProjects(ProjectTaskStatus.IN_PROGRESS);
        model.addAttribute("projects", projects);
        model.addAttribute("user", userDetails);
        return "project/projects-list-ongoing";
    }

    @GetMapping("/create")
    public String createProjectForm(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("project", new Project());
        model.addAttribute("user", userDetails);
        return "project/create";
    }

    @PostMapping("/create/new-project")
    public String createProject(@ModelAttribute(value = "project") Project project,
                                Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        projectService.createProject(userDetails.getUsername(), project.getProjectName(), project.getDescription(),
                project.getStartDate(), project.getEndDate(), project.getStatus());
        Project newProject = projectService.findProjectByProjectName(project.getProjectName());
        return String.format("redirect:/projects/%s", newProject.getId());
    }

    @GetMapping("/{project_id}/update")
    public String updateProject(@PathVariable (name = "project_id") Long projectId,
                                Model model,
                                Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Project project = projectService.findProjectById(projectId);
        model.addAttribute("project", project);
        model.addAttribute("user", userDetails);
        return "project/update";
    }

    @PostMapping("/{project_id}/updateForm")
    public String updateProject(@PathVariable (name = "project_id") Long projectId,
                                @ModelAttribute(value = "user") Project project) {

        projectService.updateProject(projectId,
                project.getProjectName(),
                project.getDescription(),
                project.getStartDate(),
                project.getEndDate(),
                project.getStatus());

        return String.format("redirect:/projects/%s", project.getId());
    }

    @GetMapping("/{project_id}/users-list")
    public String getProjectUsersList(@PathVariable (name = "project_id") Long projectId,
                             Model model,
                             Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Project project = projectService.findProjectById(projectId);
        model.addAttribute("project", project);
        model.addAttribute("user", userDetails);
        return "project/project";
    }

    @GetMapping("/{project_id}/user-invite")
    public String getProjectUserInvite(@PathVariable (name = "project_id") Long projectId,
                             Model model,
                             Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Project project = projectService.findProjectById(projectId);
        model.addAttribute("project", project);
        model.addAttribute("user", userDetails);
        return "project/project";
    }
}
