package ru.javamentor.springsecurity.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.springsecurity.model.User;
import ru.javamentor.springsecurity.service.RegistrationService;
import ru.javamentor.springsecurity.util.UserValidator;

@Controller
public class AuthCntroller {

    private RegistrationService registrationService;

    private UserValidator userValidator;

    @Autowired
    public AuthCntroller(RegistrationService registrationService, UserValidator userValidator) {
        this.registrationService = registrationService;
        this.userValidator = userValidator;
    }

    @GetMapping ("/login")
    public String loginPage() {
        return "pages/auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user) {
        return "pages/auth/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") @Valid User user,
                                      BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "pages/auth/registration";
        }
        registrationService.registration(user);
        return "redirect:/login";
    }
}

