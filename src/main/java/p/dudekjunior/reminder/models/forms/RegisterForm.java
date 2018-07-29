/*
 * Copyright (c) 2018 dudekjunior.pl
 *
 *
 */

package p.dudekjunior.reminder.models.forms;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class RegisterForm {
    @Pattern(regexp = "[a-z0-9]{6,30}")
    private String login;
    private String email;
    @Pattern(regexp = "[a-zA-Z0-9!@#$%]{6,30}")
    private String password;
    private String repeatPassword;
}
