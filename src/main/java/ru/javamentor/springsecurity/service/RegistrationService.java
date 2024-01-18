package ru.javamentor.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javamentor.springsecurity.dao.RoleDao;
import ru.javamentor.springsecurity.dao.UserDao;
import ru.javamentor.springsecurity.model.Role;
import ru.javamentor.springsecurity.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void registration(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<Role> roleList = new ArrayList<>();
        if (user.getLogin().toUpperCase().contains("ADMIN")) {
            roleList.add(roleDao.getRoleByName("ROLE_ADMIN"));
        } else {
            roleList.add(roleDao.getRoleByName("ROLE_USER"));
        }
        user.setRoles(roleList);
        userDao.addUser(user);
    }
}
