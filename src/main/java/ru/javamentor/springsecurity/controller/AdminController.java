package ru.javamentor.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javamentor.springsecurity.model.User;
import ru.javamentor.springsecurity.service.UserService;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/admin")
    public String getListUsers(ModelMap model) {
        model.addAttribute("users",userService.getListUsers());
        return "pages/admin";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "pages/new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete")
    public String deleteUser(Model model, @RequestParam(value = "id", required = false) Integer id,
                             @ModelAttribute("user") User user) {
        model.addAttribute("user",userService.getUser(id));
        return "pages/delete";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("user") User user) {
        userService.deleteUser(user);
        return "redirect:/admin";
    }
}
