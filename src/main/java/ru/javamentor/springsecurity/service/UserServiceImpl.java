package ru.javamentor.springsecurity.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javamentor.springsecurity.dao.UserDao;
import ru.javamentor.springsecurity.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }
    @Transactional
    @Override
    public void editUser(User user) {
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