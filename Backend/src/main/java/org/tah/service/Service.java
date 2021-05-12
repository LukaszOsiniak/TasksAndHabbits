package org.tah.service;

import org.springframework.stereotype.Component;
import org.tah.model.Task;
import org.tah.model.TaskStatusEnum;
import org.tah.persistence.TaskDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Service {

    private TaskDAO taskDAO = new TaskDAO();

    public int addTask(Task task) {
        validateTaskWhenAdding(task);
        try {
            taskDAO.insert(task);
            return task.getTaskId();
        } catch (SQLException e) {
            throw new ServiceException("Failed to insert a task", e);
        }
    }

    public void updateTask(Task task) {
        validateTaskWhenUpdating(task);
        try {
            taskDAO.update(task);
        } catch (SQLException e) {
            throw new ServiceException("Failed to update a task.", e);
        }
    }

    public void deleteTask(Task task) {
        try {
            taskDAO.delete(task);
        } catch (SQLException e) {
            throw new ServiceException("Failed to delete a task.", e);
        }
    }

    public Task getTask(int id) {
        Task taskFromDb;
        try {
            taskFromDb = taskDAO.getTask(id);
        } catch (SQLException e) {
            throw new ServiceException("Failed to query.", e);
        }
        return taskFromDb;
    }

    private void validateTaskWhenAdding(Task task) throws ValidationException {
        boolean isValid = true;

        if (task.getName() == null || task.getName().length() < 3) {
            isValid = false;
        }

        if (task.getStatus() == null || task.getStatus() != TaskStatusEnum.DEFINED) {
            isValid = false;
        }

        if (!isValid) {
            throw new ValidationException("Task to be added is not valid");
        }
    }

    private void validateTaskWhenUpdating(Task task) {
        boolean isValid = true;

        if (task.getName() == null || task.getName().length() < 3) {
            isValid = false;
        }

        if (task.getStatus() == null) {
            isValid = false;
        }

        if (!isValid) {
            throw new ValidationException("Task to be updated is not valid");
        }
    }

    public List<Task> getAllTasks() {
        List<Task> listOfTasks = new ArrayList<>();
        try{
            listOfTasks = taskDAO.getAllTasks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.println("task: " + listOfTasks.get(i).getName() + " " + listOfTasks.get(i).getStatus() + " " + listOfTasks.get(i).getTaskId());
        }
         return listOfTasks;
    }
}
