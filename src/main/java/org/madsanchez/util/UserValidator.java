package org.madsanchez.util;

import org.madsanchez.dao.UserDAO;
import org.madsanchez.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class UserValidator implements Validator {

    @Autowired
    @Qualifier("hibernateUserDAO")
    private UserDAO userDAO;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (userDAO.getOne(user.getEmail()) != null) {
            errors.rejectValue(
                    "email", "", "This email is already in use");
        }
    }
}
