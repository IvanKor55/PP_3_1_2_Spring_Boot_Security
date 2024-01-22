package ru.javamentor.springsecurity.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javamentor.springsecurity.dao.RoleDao;
import ru.javamentor.springsecurity.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Transactional
    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }
    @Transactional
    @Override
    public void deleteRole(Role role) {
        roleDao.deleteRole(role);
    }

    @Override
    public Role getRole(Long id) {
        return roleDao.getRole(id);
    }

    @Override
    public Role getRoleByName(String authority) {
        return roleDao.getRoleByName(authority);
    }

    @Override
    public List<Role> getListRoles (Long id) {
        return roleDao.getListRoles(id);
    }
}
