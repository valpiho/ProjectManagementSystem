package com.springboot.service.serviceImpl;

import com.springboot.entity.Project;
import com.springboot.entity.Task;
import com.springboot.entity.User;
import com.springboot.enumeration.TaskPriority;
import com.springboot.enumeration.ProjectTaskStatus;
import com.springboot.repository.TaskRepository;
import com.springboot.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public Optional<Task> findTaskById(Long id) {
        //TODO: is Optional right here?
        return taskRepository.findById(id);
    }

    @Override
    public void createTask(Project project, String description, TaskPriority priority, ProjectTaskStatus projectTaskStatus) {
        Task task = new Task();

        task.setProject(project);
        task.setTaskDescription(description);
        task.setPriority(TaskPriority.MEDIUM);
        task.setStatus(ProjectTaskStatus.NOT_STARTED);

        taskRepository.save(task);

    }

    @Override
    public void changeTaskPriority(Long id, TaskPriority priority) {
       // taskRepository.findById(id).get().setPriority(priority);
    }

    @Override
    public void changeTaskStatus(Long id, ProjectTaskStatus projectTaskStatus) {
        taskRepository.findById(id).get().setStatus(projectTaskStatus);
    }

    @Override
    public void addMembersToProject(Long id, User user) {
        //taskRepository.findById(id).get().setUser(user);
    }
}
