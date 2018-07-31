/*
 * Copyright (c) 2018 dudekjunior.pl
 *
 *
 */

package p.dudekjunior.reminder.models.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import p.dudekjunior.reminder.models.TaskEntity;

@Repository
public interface TaskRepository extends CrudRepository<TaskEntity, Integer> {
    @Modifying
    @Query("update TaskEntity t set t.isDone = ?1 where t.id = ?2")
    void setIsDoneById(boolean isDone, int taskId);
}
