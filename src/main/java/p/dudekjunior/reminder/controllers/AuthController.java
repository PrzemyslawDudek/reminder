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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import p.dudekjunior.reminder.models.forms.LoginForm;
import p.dudekjunior.reminder.models.forms.RegisterForm;
import p.dudekjunior.reminder.models.services.AuthService;
import p.dudekjunior.reminder.models.services.SessionService;

@Controller
public class AuthController {

    final AuthService authService;
    final SessionService sessionService;
    @Autowired
    public AuthController(AuthService authService, SessionService sessionService) {
        this.authService = authService;
        this.sessionService = sessionService;
    }


    @GetMapping("/login")
    public String login(){
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginForm") LoginForm loginForm,
                        RedirectAttributes redirectAttributes){
        if(authService.tryLogin(loginForm.getLogin(), loginForm.getPassword())){
            redirectAttributes.addFlashAttribute("tryLogin", "jesteś zalogowany");
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("tryLogin", "error");
        return "redirect:/";
    }

    @GetMapping("/logOut")
    public String logOut(RedirectAttributes redirectAttributes){
        sessionService.setUserEntity(null);
        sessionService.setLogin(false);
        redirectAttributes.addFlashAttribute("tryLogin", "jesteś wylogowany");
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("registerForm") RegisterForm registerForm,
                           Model model,
                           RedirectAttributes redirectAttributes){
        if(!authService.tryRegister(registerForm)){
            model.addAttribute("tryRegister", "error");
            return "register";
        }
        redirectAttributes.addFlashAttribute("tryLogin", "Zarejestrowałeś się, teraz się zaloguj");
        return "redirect:/";
    }
}
