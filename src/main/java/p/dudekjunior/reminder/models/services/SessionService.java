/*
 * Copyright (c) 2018 dudekjunior.pl
 *
 *
 */

package p.dudekjunior.reminder.models.services;

import lombok.Data;
import org.springframework.stereotype.Service;
import p.dudekjunior.reminder.models.UserEntity;

@Service
@Data
public class SessionService {
    private boolean isLogin;
    private UserEntity userEntity;
}
