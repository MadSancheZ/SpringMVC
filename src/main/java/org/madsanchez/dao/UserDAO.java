package org.madsanchez.dao;

import org.madsanchez.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@Component
public class UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getAll() throws SQLException {
        return jdbcTemplate.query("select * from users",
                new BeanPropertyRowMapper<>(User.class));
    }

    public User getOne(String email){
        return jdbcTemplate.query("select * from users where email = ?",
                new Object[] {email},
                new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
    }

    public void add(User user){
        jdbcTemplate.update("insert into users values (?, ?, ?)",
                user.getName(), user.getSurname(), user.getEmail());
    }
}
