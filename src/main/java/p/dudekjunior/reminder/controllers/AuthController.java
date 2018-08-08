/*
 * Copyright (c) 2018 dudekjunior.pl
 *
 *
 */

package p.dudekjunior.reminder.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import p.dudekjunior.reminder.models.forms.LoginForm;
import p.dudekjunior.reminder.models.forms.RegisterForm;
import p.dudekjunior.reminder.models.services.AuthService;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
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
        redirectAttributes.addFlashAttribute("tryLogin", "złe hasło lub login");
        return "redirect:/";
    }

    @GetMapping("/logOut")
    public String logOut(RedirectAttributes redirectAttributes){
        authService.logOut();
        redirectAttributes.addFlashAttribute("tryLogin", "jesteś wylogowany");
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("registerForm") @Valid RegisterForm registerForm,
                           BindingResult bindingResult,
                           Model model,
                           RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            model.addAttribute("tryRegister", "wypełnij pola poprawnie");
            return "register";
        }

        if(!registerForm.getPassword().equals(registerForm.getRepeatPassword())){
            model.addAttribute("tryRegister", "hasła nie są takie same!");
            return "register";
        }

        if(!authService.tryRegister(registerForm)){
            model.addAttribute("tryRegister", "złe hasło lub login");
            return "register";
        }
        redirectAttributes.addFlashAttribute("tryLogin", "Zarejestrowałeś się, teraz się zaloguj");
        return "redirect:/";
    }
}