package ru.javamentor.springsecurity.service;

import ru.javamentor.springsecurity.model.Role;

import java.util.List;

public interface RoleService {

    void addRole(Role role);

    void deleteRole(Role role);

    Role getRole (Long id);

    Role getRoleByName(String authority);

    List<Role> getListRoles(Long id);
}
