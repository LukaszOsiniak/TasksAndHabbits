package org.tah.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final Logger LOG = LogManager.getLogger(DbConnection.class);

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
            LOG.info("Connected to the Database.");
        } catch (SQLException e) {
            LOG.error(e, e);
        }
        return conn;
    }
}
