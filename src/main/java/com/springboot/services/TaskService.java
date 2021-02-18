package com.springboot.services;

import com.springboot.entity.Task;

public interface TaskService {

    Task findTaskById(Long id);

}
