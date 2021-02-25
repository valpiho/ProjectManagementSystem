package com.springboot.repository;

import com.springboot.entity.Project;
import com.springboot.entity.User;
import com.springboot.enumeration.ProjectTaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findProjectById(Long id);

    List<Project> findProjectByOwner_Username(String username);

    Project findProjectByProjectName(String projectName);

    List<Project> findAllByStatus(ProjectTaskStatus status);

    List<Project> findProjectsByUsers(User user);

}
