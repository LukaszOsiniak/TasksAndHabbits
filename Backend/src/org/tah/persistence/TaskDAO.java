package org.tah.persistence;

import org.tah.model.Task;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TaskDAO {

    private static final String INSERT_TASK_SQL_PART_ONE = "INSERT INTO tasks VALUES (";
    private static final String UPDATE_TASK_SQL = "UPDATE tasks SET ";
    private static final String DELETE_TASK_SQL = "DELETE FROM tasks WHERE name = ";

    public void insert(Task task) {
        String insertSQL = INSERT_TASK_SQL_PART_ONE;
        insertSQL += "'" + task.getName() + "' , '" +
                task.getStatus() + "' , " +
                task.getTaskId() + ")";
        System.out.println(insertSQL);

        DbConnection dbConnection = new DbConnection();

        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement()) {

            int rowCount = statement.executeUpdate(insertSQL);
            System.out.println("Row count for the insert: " + rowCount);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void update(Task task) {
        String updateSQL = UPDATE_TASK_SQL;
        updateSQL += "name = '" + task.getName() + "', " + "status = '" + task.getStatus() + "'" + " WHERE id = " + task.getTaskId();
        System.out.println(updateSQL);

        DbConnection dbConnection = new DbConnection();

        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement()) {
            int rowCount = statement.executeUpdate(updateSQL);
            System.out.println("Rows updated: " + rowCount);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void delete(Task task) {
        String deleteSQL = DELETE_TASK_SQL;
        deleteSQL += "'" + task.getName() + "'";
        System.out.println(deleteSQL);

        DbConnection dbConnection = new DbConnection();

        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement()) {
            int rowCount = statement.executeUpdate(deleteSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}

