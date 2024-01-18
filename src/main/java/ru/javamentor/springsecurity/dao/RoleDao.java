package ru.javamentor.springsecurity.dao;

import ru.javamentor.springsecurity.model.Role;


public interface RoleDao {
    void addRole(Role role);
    void deleteRole(Role role);
    Role getRole (Long id);
    Role getRoleByName (String authority);
}
