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
import p.dudekjunior.reminder.models.forms.LoginForm;
import p.dudekjunior.reminder.models.services.SessionService;

@Controller
public class IndexController {

    final SessionService sessionService;
    @Autowired
    public IndexController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("userSession", sessionService);
        model.addAttribute("loginForm", new LoginForm());
        return "index";
    }
}
