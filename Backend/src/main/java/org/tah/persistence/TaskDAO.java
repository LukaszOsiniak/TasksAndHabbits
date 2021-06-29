package org.tah.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.tah.model.Task;
import org.tah.model.TaskStatusEnum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    private static final Logger LOG = LogManager.getLogger(TaskDAO.class);
    private static final String INSERT_SQL = "INSERT INTO tasks (name, status, id) VALUES (?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE tasks SET name = ? , status = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM tasks WHERE id = ?";
    private static final String SELECT_SQL = "SELECT * FROM tasks WHERE id = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM tasks";
    private static final String SELECT_MAX_ID = "SELECT MAX(id) FROM tasks";


    public void insert(Task task) throws SQLException {
        DbConnection dbConnection = new DbConnection();

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement insertSt = connection.prepareStatement(INSERT_SQL);
             PreparedStatement selectIdSt = connection.prepareStatement(SELECT_MAX_ID)) {

            ResultSet rs = selectIdSt.executeQuery();
            rs.next();
            int newID = rs.getInt("MAX(id)") + 1;

            int ctr = 1;
            insertSt.setString(ctr++, task.getName());
            insertSt.setString(ctr++, task.getStatus().name());
            insertSt.setInt(ctr, newID);
            int rowCount = insertSt.executeUpdate();
            LOG.info("Number of inserted rows: " + rowCount);

            task.setTaskId(newID);
        } catch (SQLException e) {
            LOG.error("Failed when inserting a task.", e);
            throw e;
        }
    }

    public void update(Task task) throws SQLException {

        DbConnection dbConnection = new DbConnection();

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {

            int ctr = 1;
            statement.setString(ctr++, task.getName());
            statement.setString(ctr++, task.getStatus().name());
            statement.setInt(ctr, task.getTaskId());
            int rowCount = statement.executeUpdate();
            LOG.info("Number of updated rows: " + rowCount);
        } catch (SQLException e) {
            LOG.error("Failed when updating a task.", e);
            throw e;
        }
    }

    public void delete(Task task) throws SQLException {

        DbConnection dbConnection = new DbConnection();

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {

            int ctr = 1;
            statement.setInt(ctr, task.getTaskId());
            int rowCount = statement.executeUpdate();
            LOG.info("Number of deleted rows: " + rowCount);
        } catch (SQLException e) {
            LOG.error("Failed when deleting a task.", e);
            throw e;
        }
    }

    public void deleteTaskById(int deletedTaskId) throws SQLException {

        DbConnection dbConnection = new DbConnection();

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {

            int ctr = 1;
            statement.setInt(ctr, deletedTaskId);
            statement.executeUpdate();
            LOG.info("Task with id: " + deletedTaskId + " is deleted");
        } catch (SQLException e) {
            LOG.error("Failed when deleting a task.", e);
            throw e;
        }
    }

    public Task getTask(int taskId) throws SQLException {

        DbConnection dbConnection = new DbConnection();

        Task task = null;
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_SQL)) {
            statement.setInt(1, taskId);
            ResultSet rs = statement.executeQuery();
            boolean isTaskReturned = rs.next();
            if (isTaskReturned) {
                task = new Task();
                task.setName(rs.getString("name"));
                task.setStatus(TaskStatusEnum.valueOf(rs.getString("status")));
                task.setTaskId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            LOG.error("Failed when retrieving the task.", e);
            throw e;
        }
        return task;
    }

    public List<Task> getAllTasks() throws SQLException {

        DbConnection dbConnection = new DbConnection();
        Task task = null;
        List<Task> listOfAllTasks = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                task = new Task();
                task.setName(rs.getString("name"));
                task.setStatus(TaskStatusEnum.valueOf(rs.getString("status")));
                task.setTaskId(rs.getInt("id"));
                listOfAllTasks.add(task);
            }
        } catch (SQLException e) {
            LOG.error("Failed to select all tasks.", e);
            throw e;
        }
        return listOfAllTasks;
    }
}
