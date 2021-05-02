package org.tah.persistence;

import org.tah.model.Task;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TaskDAO {

    private static final String INSERT_TASK_SQL_PART_ONE = "INSERT INTO tasks VALUES (";

    public void insert(Task task) {
        String insertSQL = INSERT_TASK_SQL_PART_ONE;
        insertSQL += "'" + task.getName() + "' , '" +
                task.getStatus() + "' , " +
                task.getTaskId() + ")";
        System.out.println(insertSQL);

        DbConnection dbConnection = new DbConnection();

        Connection connection = null;
        Statement statement = null;
        try {
            connection = dbConnection.getConnection();
            statement = connection.createStatement();
            int rowCount = statement.executeUpdate(insertSQL);
            System.out.println("Row count for the insert: " + rowCount);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }

    }
}
