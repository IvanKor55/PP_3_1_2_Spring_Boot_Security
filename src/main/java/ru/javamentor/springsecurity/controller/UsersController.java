package ru.javamentor.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javamentor.springsecurity.model.Role;
import ru.javamentor.springsecurity.model.User;
import ru.javamentor.springsecurity.service.UserService;

import java.util.List;

import static ru.javamentor.springsecurity.config.SuccessUserHandler.getLooginRole;

@Controller
public class UsersController {
    private List<Role> roles;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user")
    public String getUser(Model model, @RequestParam(value = "id", required = false) Integer id) {
        model.addAttribute("user",userService.getUser(id));
        return "pages/user";
    }

    @GetMapping("/edit")
    public String editUser(Model model, @RequestParam(value = "id", required = false) Integer id,
                           @ModelAttribute("user") User user) {
        user = userService.getUser(id);
        roles = user.getRoles();
        model.addAttribute("user",user);
        return "pages/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("user") User user) {
        user.setRoles(roles);
        userService.editUser(user);
        if (getLooginRole().equals("ROLE_ADMIN")) {
            return "redirect:/admin";
        } else {
            return "redirect:/user?id=" + user.getId();
        }
    }
}
