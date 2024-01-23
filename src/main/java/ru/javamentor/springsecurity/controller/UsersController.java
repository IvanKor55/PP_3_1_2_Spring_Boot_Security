package ru.javamentor.springsecurity.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.springsecurity.model.Role;
import ru.javamentor.springsecurity.model.User;
import ru.javamentor.springsecurity.service.RoleService;
import ru.javamentor.springsecurity.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static ru.javamentor.springsecurity.config.SuccessUserHandler.getLooginRole;

@Controller
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "")
    public String getUser(Model model, @RequestParam(value = "id", required = false) Long id) {
        model.addAttribute("user",userService.getUser(id));
        return "pages/user";
    }

    @GetMapping("/edit")
    public String editUser(Model model, @RequestParam(value = "id", required = false) Long id) {
        model.addAttribute("user",userService.getUser(id));
        model.addAttribute("roles",roleService.getListRoles(id));
        return "pages/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("user") User user, HttpServletRequest request) {
        String[] rolesList = request.getParameterValues("rolesList");
        List<Role> roles = new ArrayList<>();
        for (String roleName : rolesList) {
            String[] rolesListAdd = roleName.split(" ");
            for (String roleS : rolesListAdd) {
                Role role = roleService.getRoleByName("ROLE_" + roleS);
                if (role != null) {
                    roles.add(role);
                }
            }
        }
        if (!roles.isEmpty()) {
            user.setRoles(roles);
        }
        userService.editUser(user);
        if (getLooginRole().equals("ROLE_ADMIN")) {
            return "redirect:/admin";
        } else {
            return "redirect:/user?id=" + user.getId();
        }
    }
}
