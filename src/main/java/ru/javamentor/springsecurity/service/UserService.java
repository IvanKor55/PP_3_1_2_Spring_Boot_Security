package ru.javamentor.springsecurity.service;
import ru.javamentor.springsecurity.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    void editUser(User user, String[] rolesList);

    void deleteUser(User user);

    List<User> getListUsers();

    User getUser (Long id);

    User findByLogin (String login);
}
