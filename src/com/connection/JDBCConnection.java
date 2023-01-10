package com.connection;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    public static Connection testConnection() {
        final String URL = "jdbc:mysql://localhost:3306/classroom";
        final String user = "root";
        final String password = "myadmin";

        // install mysql driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, user, password);

            return connection;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return null;
    }
}
