package ru.javamentor.springsecurity.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javamentor.springsecurity.model.User;
import ru.javamentor.springsecurity.service.UserService;

@Component
public class UserValidator implements Validator {

    private UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        User user = (User) object;
        if (userService.findByLogin(user.getLogin()) == null) {
            return;
        }
        errors.rejectValue("login", "", "Такой логин уже существует");
    }
}
