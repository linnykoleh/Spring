package com.demo.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.demo.dao.UserDao;
import com.demo.to.User;

@Repository
@Component
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserDaoImpl implements UserDao {

    private final BasicDataSource ds;

    @Autowired
    public UserDaoImpl(BasicDataSource ds) {
        this.ds = ds;
    }

    @Override
    public String save(User u) {
        Connection con;
        PreparedStatement ps = null;
        String sql = "insert into users (username,password,address,phone) values(?,?,?,?)";
        try {
            con = ds.getConnection();
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, u.getUserName());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getAddress());
            ps.setString(4, u.getPhone());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return "User already Exists! Please choose a different user name";
        }
        return "Successful";
    }

    @Override
    public void update(User u) {
        // For additional functionality implement this method
    }

    @Override
    public String authenticateUser(User u) {
        Connection con;
        PreparedStatement ps = null;
        String sql = "select count(*) from users where username=? and password=?";
        try {
            con = ds.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getUserName());
            ps.setString(2, u.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) == 0) {
                    return "Either Username or Password is incorrect.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Either Username or Password is incorrect.";
        }
        return "Successful";
    }

    @Override
    public List<User> list() {
        // For additional functionality implement this method
        return null;
    }
}
