package com.springboot.controller;

import com.springboot.entity.Project;
import com.springboot.entity.User;
import com.springboot.enumeration.ProjectTaskStatus;
import com.springboot.service.ProjectService;
import com.springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/{project_id}")
    public String getProject(@PathVariable (name = "project_id") Long projectId, Model model) {
        Project project = projectService.findProjectById(projectId);
        model.addAttribute("project", project);
        return "project";
    }

    @GetMapping("/all-projects")
    public String getAllProjects() {
        projectService.findAllProjects();
        return "";
    }

    @GetMapping("/{username}")
    public String getAllProjectsByUser(@PathVariable (name = "username") String username, Model model) {
        User user = userService.findUserByUsername(username);
        List<Project> projects = projectService.findProjectsByUsers(user);
        model.addAttribute(projects);
        return "";
    }

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
        return "project-add";
    }

    @PostMapping("/create/new-project")
    public String createProject(@ModelAttribute(value = "project") Project project) {

        projectService.createProject(project.getProjectName(),
                                        project.getDescription(),
                                        project.getStartDate(),
                                        project.getEndDate(),
                                        project.getStatus());
        return "project-add";
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
