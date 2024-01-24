package ru.javamentor.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javamentor.springsecurity.model.Role;
import ru.javamentor.springsecurity.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationService {

    private RoleService roleService;

    private UserService userService;

    private PasswordEncoder passwordEncoder;
    
    @Autowired
    public RegistrationService(RoleService roleService, UserService userService, PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registration(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<Role> roleList = new ArrayList<>();
        if (user.getLogin().toUpperCase().contains("ADMIN")) {
            roleList.add(roleService.getRoleByName("ROLE_ADMIN"));
        } else {
            roleList.add(roleService.getRoleByName("ROLE_USER"));
        }
        user.setRoles(roleList);
        userService.addUser(user);
    }
}
