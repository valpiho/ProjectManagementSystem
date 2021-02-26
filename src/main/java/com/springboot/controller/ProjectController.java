package com.springboot.controller;

import com.springboot.entity.Project;
import com.springboot.entity.User;
import com.springboot.service.ProjectService;
import com.springboot.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.springboot.enumeration.ProjectTaskStatus.*;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    public ProjectController(ProjectService projectService,
                             UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/{project_id}")
    public String getProject(@PathVariable (name = "project_id") Long projectId,
                             Model model, Authentication authentication) {
        Project project = projectService.findProjectById(projectId);
        model.addAttribute("project", project);
        model.addAttribute("authUser", getAuthUser(authentication));
        return "project/project";
    }

    @GetMapping("/projects-list")
    public String getAllProjects(Model model, Authentication authentication) {
        List<Project> projects = projectService.findAllProjects();
        model.addAttribute("projects", projects);
        model.addAttribute("authUser", getAuthUser(authentication));
        return "project/projects-list";
    }

    @GetMapping("/projects-list-ongoing")
    // TODO: Get all ongoing projects
    public String getAllOngoingProjects(Model model, Authentication authentication) {
        List<Project> projects = projectService.findAllProjectsByStatus(IN_PROGRESS);
        model.addAttribute("projects", projects);
        model.addAttribute("authUser", getAuthUser(authentication));
        return "project/projects-list-ongoing";
    }

    @GetMapping("/projects-list-completed")
    // TODO: Get all completed projects
    public String getAllCompletedProjects(Model model, Authentication authentication) {
        List<Project> projects = projectService.findAllProjectsByStatus(COMPLETED);
        model.addAttribute("projects", projects);
        model.addAttribute("authUser", getAuthUser(authentication));
        return "project/projects-list-completed";
    }

    @GetMapping("/projects-list-archived")
    // TODO: Get all archived projects
    public String getAllArchivedProjects(Model model, Authentication authentication) {
        List<Project> projects = projectService.findAllProjectsByStatus(ARCHIVED);
        model.addAttribute("projects", projects);
        model.addAttribute("authUser", getAuthUser(authentication));
        return "project/projects-list-archived";
    }

    @GetMapping("/create")
    public String createProjectForm(Model model, Authentication authentication) {
        model.addAttribute("project", new Project());
        model.addAttribute("authUser", getAuthUser(authentication));
        return "project/create";
    }

    @PostMapping("/create/new-project")
    public String createProject(@ModelAttribute(value = "project") Project project,
                                Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        projectService.createProject(userDetails.getUsername(),
                                    project.getProjectName(),
                                    project.getDescription(),
                                    project.getStartDate(),
                                    project.getEndDate());

        Project newProject = projectService.findProjectByProjectName(project.getProjectName());
        return String.format("redirect:/projects/%s", newProject.getId());
    }

    @GetMapping("/{project_id}/update")
    public String updateProject(@PathVariable (name = "project_id") Long projectId,
                                Model model, Authentication authentication) {
        Project project = projectService.findProjectById(projectId);
        model.addAttribute("project", project);
        model.addAttribute("authUser", getAuthUser(authentication));
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
    // TODO: Get all users for current project
    public String getProjectUsersList(@PathVariable (name = "project_id") Long projectId,
                             Model model, Authentication authentication) {
        Project project = projectService.findProjectById(projectId);
        model.addAttribute("project", project);
        model.addAttribute("authUser", getAuthUser(authentication));
        return "project/project";
    }

    @GetMapping("/{project_id}/user-invite")
    // TODO: Invite user for project
    public String getProjectUserInvite(@PathVariable (name = "project_id") Long projectId,
                             Model model, Authentication authentication) {
        Project project = projectService.findProjectById(projectId);
        model.addAttribute("project", project);
        model.addAttribute("authUser", getAuthUser(authentication));
        return "project/project";
    }

    private User getAuthUser(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userService.findUserByUsername(userDetails.getUsername());
    }
}
