package org.madsanchez.util;

import org.madsanchez.dao.UserDAO;
import org.madsanchez.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
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
