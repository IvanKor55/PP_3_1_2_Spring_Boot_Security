package ru.javamentor.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.springsecurity.service.UserService;

@Controller
@RequestMapping("/user")
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUser(Model model, @RequestParam(value = "id", required = false) Long id) {
        model.addAttribute("user",userService.getUser(id));
        return "pages/user";
    }
}
