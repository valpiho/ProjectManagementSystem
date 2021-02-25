package com.springboot.repository;

import com.springboot.entity.Task;
import com.springboot.entity.User;
import com.springboot.enumeration.ProjectTaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findById(long id);

    List<Task> findByProjectId(Long id);

    List<Task> findAllByUserUsername(String username);

    List<Task> findAllByStatus(ProjectTaskStatus status);
}
