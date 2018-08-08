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
import p.dudekjunior.reminder.models.TaskEntity;
import p.dudekjunior.reminder.models.forms.TaskForm;
import p.dudekjunior.reminder.models.services.TaskService;

import java.util.List;

@Controller
public class TaskController {

    private final TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/addTask")
    public String addPost(Model model){
        model.addAttribute("taskForm", new TaskForm());
        return "addtask";
    }

    @PostMapping("/addTask")
    public String addPost(@ModelAttribute("taskForm") TaskForm taskForm){
        taskService.addTask(taskForm);
        return "redirect:/";
    }

    @GetMapping("/task/isDone/{taskId}/{isDone}")
    public String taskIsDone(@PathVariable("taskId") int taskId,
                             @PathVariable("isDone") boolean isDone){
        taskService.taskIsDone(taskId, isDone);
        return "redirect:/";
    }

    @GetMapping("task/delete/{taskId}")
    public String deleteTask(@PathVariable("taskId") int taskId){
        taskService.deleteTaskById(taskId);
        return "redirect:/";
    }

    @GetMapping("/allUserTasks")
    public String showAllTask(RedirectAttributes redirectAttributes){
        List<TaskEntity> tasks = taskService.allUserTasks();
        redirectAttributes.addFlashAttribute("tasks", tasks);
        return "redirect:/allTasks";
    }
    @GetMapping("/todayTask")
    public String showTodayTask(){
        taskService.tasksForToday();
        return "redirect:/";
    }

    @GetMapping("/task/{taskId}")
    public String showTask(@PathVariable("taskId") int taskId,
                           Model model){
        TaskEntity task = taskService.getTaskById(taskId);

        model.addAttribute("task", task);
        return "task";
    }
}
