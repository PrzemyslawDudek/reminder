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

    public TaskEntity findTaskById(int postId) {
        return taskRepository.findById(postId).get();
    }

    public void taskIsDone(int taskId) {
        taskRepository.save(changeTaskIsDone(taskId));
    }

    private TaskEntity changeTaskIsDone(int taskId){
        TaskEntity taskEntity = taskRepository.findById(taskId).get();

        taskEntity.setDone(true);

        return taskEntity;
    }
}
