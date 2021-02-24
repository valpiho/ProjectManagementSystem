package com.springboot.repository;

import com.springboot.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findById(long id);
    Task findByIdAndProjectId(Long taskId, Long projectId);
    List<Task> findByProjectId(Long id);
    List<Task> findAllByUsername(String username);
}
