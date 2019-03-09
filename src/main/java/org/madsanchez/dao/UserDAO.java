package org.madsanchez.dao;

import org.madsanchez.model.User;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@Component
public class UserDAO {

    private static Connection conn;

    static {
        String url = null;
        String username = null;

        try(InputStream input = UserDAO.class.getClassLoader()
                                .getResourceAsStream("persistence.properties")){

//            Properties props = new Properties();
//            props.load(input);
//            url = props.getProperty("url");
//            username = props.getProperty("username");
//            System.out.println(url+" "+username);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Java", "postgres", "");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("select * from users");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            User user = new User();
            user.setName(rs.getString(1));
            user.setSurname(rs.getString(2));
            user.setEmail(rs.getString(3));
            users.add(user);
        }
        return users;
    }

    public User getOne(String email) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select * from users where email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                User user = new User();
                user.setName(rs.getString(1));
                user.setSurname(rs.getString(2));
                user.setEmail(rs.getString(3));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
