/*
 * Copyright (c) 2018 dudekjunior.pl
 *
 *
 */

package p.dudekjunior.reminder.models.forms;

import lombok.Data;

@Data
public class RegisterForm {
    private String login;
    private String email;
    private String password;
    private String repeatPassword;
}
