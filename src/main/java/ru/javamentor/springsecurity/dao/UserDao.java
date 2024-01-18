package ru.javamentor.springsecurity.dao;

import ru.javamentor.springsecurity.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    void editUser(User user);
    void deleteUser(User user);
    List<User> getListUsers();
    User getUser (Long id);
    User findByLogin (String login);
}
