package org.madsanchez.dao;

import org.madsanchez.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    List<User> getAll();

    User getOne(String email);

    void add(User user);
}
