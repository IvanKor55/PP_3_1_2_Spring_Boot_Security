package ru.javamentor.springsecurity.service;

import ru.javamentor.springsecurity.model.Role;

public interface RoleService {
    void addRole(Role role);
    void deleteRole(Role role);
    Role getRole (Long id);
}
