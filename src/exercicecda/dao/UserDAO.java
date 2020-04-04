/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicecda.dao;

import exercicecda.entity.User;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author Mounir
 */
public class UserDAO {

    public static int insert(User user) throws SQLException {
        String sql = "INSERT INTO user (email, password, birth_date) VALUES (?, ?, ?)";
        Connection conn = DAO.getConnection();
        if (conn == null) {
            return -1;
        }
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getEmail());
        statement.setString(2, user.getPassword());
        statement.setDate(3, java.sql.Date.valueOf(user.getBirthDate()));

        return statement.executeUpdate();
    }
    
    public static List<User> selectAll() throws SQLException {
        String sql = "SELECT * FROM user";
        Connection conn = DAO.getConnection();
        if (conn == null) {
            return null;
        }
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        List<User> users = new ArrayList<User>();
        User user;
        while (result.next()){
            user = new User();
            user.setId(result.getInt(1));
            user.setEmail(result.getString(2));
            user.setPassword(result.getString(3));
            user.setBirthDate(result.getDate(4).toLocalDate());
            users.add(user);
        }
        return users;
    }
    
    public static User selectByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM user WHERE email='" + email + "'";
        Connection conn = DAO.getConnection();
        if (conn == null) {
            return null;
        }
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        User user = null;
        if (result.next()) {
            user = new User();
            user.setId(result.getInt(1));
            user.setEmail(result.getString(2));
            user.setPassword(result.getString(3));
            user.setBirthDate(result.getDate(4).toLocalDate());
        }
        return user;
    }
    
    public static int update(User user) throws SQLException {
        String sql = "UPDATE user SET email=?, password=?, birth_date=? WHERE id=?";
        Connection conn = DAO.getConnection();
        if (conn == null) {
            return -1;
        }
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getEmail());
        statement.setString(2, user.getPassword());
        statement.setDate(3, java.sql.Date.valueOf(user.getBirthDate()));
        statement.setInt(4, user.getId());

        return statement.executeUpdate();
    }
    
    public static int delete(int userId) throws SQLException {
        String sql = "DELETE FROM user WHERE id=?";
        Connection conn = DAO.getConnection();
        if (conn == null) {
            return -1;
        }
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, userId);

        return statement.executeUpdate();
    }
}
