package org.tah.service;

import org.junit.jupiter.api.Test;
import org.tah.model.Task;
import org.tah.model.TaskStatusEnum;
import org.tah.service.test.util.IntegrationTestBase;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ServiceIntegrationTest extends IntegrationTestBase {

    private Service service = new Service();

    @Test
    public void shouldAddTaskToDB() {
        //given
        Task taskOne = new Task();
        taskOne.setName("Task 123");
        taskOne.setStatus(TaskStatusEnum.DEFINED);
        taskOne.setTaskId(1);
        //when
        service.addTask(taskOne);
        Task taskInDB = service.getTask(1);
        //then
        assertEquals(taskInDB.getName(), taskOne.getName());
        assertEquals(taskInDB.getStatus(), taskOne.getStatus());
        assertEquals(taskInDB.getTaskId(), taskOne.getTaskId());
    }

    @Test
    public void shouldUpdateTask() throws SQLException {
        //given
        runSqlDataloadScript("/dataload/insertTask.sql");
        Task task = new Task();
        task.setName("Updated task");
        task.setStatus(TaskStatusEnum.COMPLETE);
        task.setTaskId(2);
        //when
        service.updateTask(task);
        Task updatedTask = service.getTask(2);
        //then
        assertEquals(updatedTask.getName(), task.getName());
        assertEquals(updatedTask.getStatus(), task.getStatus());
        assertEquals(updatedTask.getTaskId(), task.getTaskId());
    }

    @Test
    public void shouldDeleteTask() throws SQLException {
        //given
        runSqlDataloadScript("/dataload/insertTask.sql");
        Task task = new Task();
        task.setTaskId(2);
        //when
        service.deleteTask(task);
        Task deletedTask = service.getTask(2);
        //then
        assertNull(deletedTask);
    }
}
