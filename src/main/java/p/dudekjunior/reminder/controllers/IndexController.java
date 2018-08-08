/*
 * Copyright (c) 2018 dudekjunior.pl
 *
 *
 */

package p.dudekjunior.reminder.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import p.dudekjunior.reminder.models.TaskEntity;
import p.dudekjunior.reminder.models.forms.LoginForm;
import p.dudekjunior.reminder.models.services.SessionService;
import p.dudekjunior.reminder.models.services.TaskService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class IndexController {

    private final SessionService sessionService;
    private final TaskService taskService;
    @Autowired
    public IndexController(SessionService sessionService, TaskService taskService) {
        this.sessionService = sessionService;
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String index(Model model){
        if(sessionService.isLogin()){
            List<TaskEntity> tasks = new ArrayList<>(taskService.tasksForToday());
            Collections.reverse(tasks);
            model.addAttribute("tasks", tasks);
        }
        model.addAttribute("userSession", sessionService);
        model.addAttribute("loginForm", new LoginForm());
        return "index";
    }
    @GetMapping("/allTasks")
    public String index(Model model, @ModelAttribute("tasks") List<TaskEntity> tasks){
        Collections.reverse(tasks);
        model.addAttribute("tasks", tasks);
        model.addAttribute("userSession", sessionService);
        return "index";
    }
}
