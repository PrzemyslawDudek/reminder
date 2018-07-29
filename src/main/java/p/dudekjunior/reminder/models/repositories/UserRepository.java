/*
 * Copyright (c) 2018 dudekjunior.pl
 *
 *
 */

package p.dudekjunior.reminder.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import p.dudekjunior.reminder.models.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUserLoginAndPassword(String userLogin, String password);
    boolean existsByUserLoginOrUserEmail(String login, String email);
}
