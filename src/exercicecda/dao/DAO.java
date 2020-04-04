/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicecda.dao;

import java.sql.*;

/**
 *
 * @author Mounir
 */
public class DAO {
    private final static String DB_URL = "jdbc:mysql://localhost:3306/cda?serverTimezone=UTC";
    private final static String USER_NAME = "root";
    private final static String PASSWORD = "Admin12#";
    private static Connection connection;

    public static boolean connect() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static boolean disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static Connection getConnection() {
        return connection;
    }
}
