package org.tah.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private Connection connection;

    public Connection getConnection() {
        if (connection == null) {
            this.connection = connect();
        }
        return connection;
    }

    private Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:D:/code/TasksAndHabbits/tahDB.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connected");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
