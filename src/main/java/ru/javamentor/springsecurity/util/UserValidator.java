package ru.javamentor.springsecurity.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javamentor.springsecurity.model.User;
import ru.javamentor.springsecurity.service.UserDetailServiceImpl;
import ru.javamentor.springsecurity.service.UserService;

/**
 * @author Neil Alishev
 */
@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        User user = (User) object;
        if (userService.findByLogin(user.getLogin()) == null) {
            return; // все ок, пользователь не найден
        }
        errors.rejectValue("login", "", "Такой логин уже существует");
    }
}
