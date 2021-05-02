package org.tah.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.tah.model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaskDAO {

    private static final Logger LOG = LogManager.getLogger(TaskDAO.class);
    private static final String INSERT_SQL = "INSERT INTO tasks VALUES (?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE tasks SET name = ? , status = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM tasks WHERE name = ?";

    public void insert(Task task) {

        DbConnection dbConnection = new DbConnection();

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
            statement.setString(1, task.getName());
            statement.setString(2, task.getStatus().name());
            statement.setInt(3, task.getTaskId());

            int rowCount = statement.executeUpdate();
            LOG.info("Number of inserted rows" + rowCount);
        } catch (SQLException e) {
            LOG.error(e, e);
        }
    }

    public void update(Task task) {

        DbConnection dbConnection = new DbConnection();

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, task.getName());
            statement.setString(2, task.getStatus().name());
            statement.setInt(3, task.getTaskId());
            int rowCount = statement.executeUpdate();
            LOG.info("Number of updated rows" + rowCount);
        } catch (SQLException e) {
            LOG.error(e, e);
        }
    }

    public void delete(Task task) {

        DbConnection dbConnection = new DbConnection();

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setString(1, task.getName());
            int rowCount = statement.executeUpdate();
            LOG.info("Number of deleted rows" + rowCount);
        } catch (SQLException e) {
            LOG.error(e, e);
        }
    }
}
