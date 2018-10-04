package com.demo.dao;

import com.demo.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class UserDao {

    private final PasswordEncoder passwordEncoder;

    private final DriverManagerDataSource ds;

    @Autowired
    public UserDao(PasswordEncoder passwordEncoder, DriverManagerDataSource ds) {
        this.passwordEncoder = passwordEncoder;
        this.ds = ds;
    }

    public String save(UserTo userto) {
        final UserTo user = new UserTo();
        user.setUsername(userto.getUsername());
        user.setPassword(passwordEncoder.encode(userto.getPassword()));

        Connection con = null;
        PreparedStatement ps = null;
        boolean enabled = true;
        String sql = "insert into users (username,password,enabled) values(?,?,?)";
        try {
            con = ds.getConnection();
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setBoolean(3, enabled);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return "User already Exists! Please choose a different user name";
        }

        final String authQuery = "insert into user_roles  (username,role) values('" + user.getUsername() + "','ROLE_USER')";
        try {
            con = ds.getConnection();
            Statement s = con.createStatement();
            s.executeUpdate(authQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed";
        }
        return "Successful";
    }

}
