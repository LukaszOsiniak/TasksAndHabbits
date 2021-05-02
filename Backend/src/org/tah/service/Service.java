package org.tah.service;

import org.tah.model.Task;
import org.tah.model.TaskStatusEnum;
import org.tah.persistence.TaskDAO;

public class Service {

    private TaskDAO taskDAO = new TaskDAO();

    public void addTask(Task task) {
        taskDAO.insert(task);
    }

    public void updateTask(Task task) {
        taskDAO.update(task);
    }

    public void deleteTask(Task task) {
        taskDAO.delete(task);
    }

    private static void validateTaskWhenAdding(Task task) {
        boolean isValid = true;

        if (task.getName() == null || task.getName().length() < 3) {
            isValid = false;
        }

        if (task.getStatus() == null || task.getStatus() != TaskStatusEnum.DEFINED) {
            isValid = false;
        }

        if (!isValid) {
            throw new RuntimeException("task to be added is not valid");
        }
    }

    public static void main(String[] args) {
        Task testowyTask = new Task();
        testowyTask.setTaskId(1);
        testowyTask.setName("nowy task 1");
        testowyTask.setStatus(TaskStatusEnum.DEFINED);

        Service s = new Service();
        s.addTask(testowyTask);

        testowyTask.setName("New task 1");
        s.updateTask(testowyTask);

        testowyTask.setStatus(TaskStatusEnum.COMPLETE);
        s.updateTask(testowyTask);

        s.deleteTask(testowyTask);
    }
}
