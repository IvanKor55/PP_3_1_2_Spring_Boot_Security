package ru.javamentor.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.springsecurity.dao.RoleDao;
import ru.javamentor.springsecurity.model.Role;
import ru.javamentor.springsecurity.model.User;
import ru.javamentor.springsecurity.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleDao roleDao;

    @GetMapping(value = "")
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
        List<Role> roleList = new ArrayList<>();
        roleList.add(roleDao.getRoleByName("ROLE_USER"));
        user.setRoles(roleList);
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete")
    public String deleteUser(Model model, @RequestParam(value = "id", required = false) Long id,
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
