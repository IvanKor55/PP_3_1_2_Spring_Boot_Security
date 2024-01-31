package ru.javamentor.springsecurity.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javamentor.springsecurity.dao.UserDao;
import ru.javamentor.springsecurity.model.Role;
import ru.javamentor.springsecurity.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private RoleService roleService;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleService roleService) {
        this.userDao = userDao;
        this.roleService = roleService;
    }

    @Transactional
    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Transactional
    @Override
    public void editUser(User user, String[] rolesList) {
//        List<Role> roles = Arrays.stream(rolesList)
//                .flatMap(roleNames -> Arrays.stream(roleNames.split(" ")))
//                .map(roleName -> roleService.getRoleByName("ROLE_" + roleName))
//                .filter(Objects::nonNull)
//                .collect(Collectors.toList());
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
        userDao.editUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Override
    public List<User> getListUsers() {
        return userDao.getListUsers();
    }

    @Override
    public User getUser (Long id) {
        return userDao.getUser(id);
    }

    @Override
    public User findByLogin (String login) {
        return userDao.findByLogin(login);
    }
}