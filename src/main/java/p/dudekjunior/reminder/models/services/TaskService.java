/*
 * Copyright (c) 2018 dudekjunior.pl
 *
 *
 */

package p.dudekjunior.reminder.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import p.dudekjunior.reminder.models.TaskEntity;
import p.dudekjunior.reminder.models.forms.TaskForm;
import p.dudekjunior.reminder.models.repositories.TaskRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final SessionService sessionService;

    @Autowired
    public TaskService(TaskRepository taskRepository, SessionService sessionService) {
        this.taskRepository = taskRepository;
        this.sessionService = sessionService;

    }

    public void addTask(TaskForm taskForm){
        taskRepository.save(createTaskEntity(taskForm));
    }

    private TaskEntity createTaskEntity(TaskForm taskForm){
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTaskTitle(taskForm.getTaskTitle());
        taskEntity.setTaskContext(taskForm.getTaskContext());
        taskEntity.setDone(false);
        taskEntity.setUser(sessionService.getUserEntity());
        return taskEntity;
    }

    public void deleteTaskById(int taskId) {
        taskRepository.deleteById(taskId);
    }

    public void taskIsDone(int taskId, boolean isDone) {
        Optional<List<TaskEntity>> tasks = taskRepository.findByUser(sessionService.getUserEntity());
        if(!tasks.isPresent()){
            return;
        }
        Optional<TaskEntity> taskIsDone = tasks.get().stream().filter(task -> task.getId() == taskId).findFirst();
        if(!taskIsDone.isPresent()){
            return;
        }
        taskIsDone.get().setDone(isDone);
        taskRepository.save(taskIsDone.get());
    }

    public List<TaskEntity> tasksForToday(){
        Optional<List<TaskEntity>> tasks = taskRepository.findByUser(sessionService.getUserEntity());
        if(!tasks.isPresent()){
            return new ArrayList<>();
        }
        return tasks.get()
                .stream()
                .filter(task -> !task.isDone())
                .filter(task -> task.getTaskDate().getDayOfMonth()==LocalDateTime.now().getDayOfMonth())
                .collect(Collectors.toList());
    }

    public List<TaskEntity> allUserTasks(){
        Optional<List<TaskEntity>> tasks = taskRepository.findByUser(sessionService.getUserEntity());
        return tasks.orElseGet(ArrayList::new);
    }

    public TaskEntity getTaskById(int taskId) {
        return taskRepository.findById(taskId).orElseGet(TaskEntity::new);
    }
}
