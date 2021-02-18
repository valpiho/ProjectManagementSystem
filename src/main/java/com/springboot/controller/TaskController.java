package com.springboot.controller;

import com.springboot.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class TaskController {

    private TaskService taskService;
}
