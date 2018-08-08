/*
 * Copyright (c) 2018 dudekjunior.pl
 *
 *
 */

package p.dudekjunior.reminder.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import p.dudekjunior.reminder.models.TaskEntity;
import p.dudekjunior.reminder.models.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<TaskEntity, Integer> {
    Optional<List<TaskEntity>> findByUser(UserEntity userEntity);
}
