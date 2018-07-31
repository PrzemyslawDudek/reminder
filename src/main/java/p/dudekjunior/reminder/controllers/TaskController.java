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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import p.dudekjunior.reminder.models.forms.TaskForm;
import p.dudekjunior.reminder.models.services.SessionService;
import p.dudekjunior.reminder.models.services.TaskService;

@Controller
public class TaskController {

    private final TaskService taskService;
    private final SessionService sessionService;
    @Autowired
    public TaskController(TaskService taskService, SessionService sessionService) {
        this.taskService = taskService;
        this.sessionService = sessionService;
    }

    @GetMapping("/addTask")
    public String addPost(Model model){
        model.addAttribute("taskForm", new TaskForm());
        return "addtask";
    }

    @PostMapping("/addTask")
    public String addPost(@ModelAttribute("taskForm") TaskForm taskForm,
                          RedirectAttributes redirectAttributes){
        taskService.addTask(taskForm);
        return "redirect:/";
    }

    @GetMapping("/task/{taskId}")
    public String taskIsDone(@PathVariable("taskId") int taskId){
        if(sessionService.getUserEntity().getId()!=taskService.findTaskById(taskId).getUser().getId()){
            return "redirect:/";
        }
        taskService.taskIsDone(taskId);
        return "redirect:/";
    }
}
