/*
 * Copyright (c) 2018 dudekjunior.pl
 *
 *
 */

package p.dudekjunior.reminder.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import p.dudekjunior.reminder.models.UserEntity;
import p.dudekjunior.reminder.models.forms.RegisterForm;
import p.dudekjunior.reminder.models.repositories.UserRepository;

import java.util.Optional;

@Service
public class AuthService {

    final UserRepository userRepository;
    final SessionService sessionService;
    @Autowired
    public AuthService(UserRepository userRepository, SessionService sessionService) {
        this.userRepository = userRepository;
        this.sessionService = sessionService;
    }

    public boolean tryLogin(String login, String password){
        Optional<UserEntity> userEntity = userRepository.findByUserLoginAndPassword(login, password);
        if(userEntity.isPresent()){
            sessionService.setLogin(true);
            sessionService.setUserEntity(userEntity.get());
        }
        return userEntity.isPresent();
    }

    public boolean tryRegister(RegisterForm registerForm) {
        if(userRepository.existsByUserLoginOrUserEmail(registerForm.getLogin(), registerForm.getEmail())){
            return false;
        }
        userRepository.save(createUserEntity(registerForm));

        return true;
    }

    private UserEntity createUserEntity(RegisterForm registerForm) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserLogin(registerForm.getLogin());
        userEntity.setUserEmail(registerForm.getEmail());
        userEntity.setPassword(registerForm.getPassword());
        return userEntity;
    }
}
