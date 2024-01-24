package ru.javamentor.springsecurity.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.springsecurity.model.Role;
import ru.javamentor.springsecurity.model.User;
import ru.javamentor.springsecurity.service.RoleService;
import ru.javamentor.springsecurity.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static ru.javamentor.springsecurity.config.SuccessUserHandler.getLooginRole;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String getListUsers(ModelMap model) {
        model.addAttribute("users",userService.getListUsers());
        return "pages/admin";
    }

    @GetMapping("/edit")
    public String editUser(Model model, @RequestParam(value = "id", required = false) Long id) {
        model.addAttribute("user",userService.getUser(id));
        model.addAttribute("roles",roleService.getListRoles(id));
        return "pages/edit";
    }

    @PostMapping("/edit")
    public String updateUser(@ModelAttribute("user") User user, HttpServletRequest request) {
        userService.editUser(user, request.getParameterValues("rolesList"));
        if (getLooginRole().equals("ROLE_ADMIN")) {
            return "redirect:/admin";
        } else {
            return "redirect:/user?id=" + user.getId();
        }
    }
    @GetMapping("/new")
    public String createUser(@ModelAttribute("user") User user) {
        return "pages/new";
    }

    @PostMapping("/new")
    public String addUser(@ModelAttribute("user") User user) {
        List<Role> roleList = new ArrayList<>();
        roleList.add(roleService.getRoleByName("ROLE_USER"));
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
    public String removeUser(@ModelAttribute("user") User user) {
        userService.deleteUser(user);
        return "redirect:/admin";
    }
}
