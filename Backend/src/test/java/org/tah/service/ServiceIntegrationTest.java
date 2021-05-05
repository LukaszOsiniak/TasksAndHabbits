package org.tah.service;

import org.junit.jupiter.api.Test;
import org.tah.model.Task;
import org.tah.model.TaskStatusEnum;
import org.tah.service.test.util.IntegrationTestBase;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        Task taskInDB = service.query(1);
        //then
        assertEquals(taskInDB.getName(), taskOne.getName());
        assertEquals(taskInDB.getStatus(), taskOne.getStatus());
        assertEquals(taskInDB.getTaskId(), taskOne.getTaskId());
    }

    @Test
    public void shouldUpdateTask() throws SQLException {
        //given
        Task task = new Task();
        task.setName("Updated task");
        task.setStatus(TaskStatusEnum.COMPLETE);
        task.setTaskId(2);
        runSqlDataloadScript("/dataload/insertTask.sql");
        //when
        service.updateTask(task);
        Task updatedTask = service.query(2);
        //then
        assertEquals(updatedTask.getName(), task.getName());
        assertEquals(updatedTask.getStatus(), task.getStatus());
        assertEquals(updatedTask.getTaskId(), task.getTaskId());
    }

    @Test
    public void shouldDeleteTask() throws SQLException {
        //given
        Task task = new Task();
        task.setTaskId(2);
        runSqlDataloadScript("/dataload/insertTask.sql");
        //when
        service.deleteTask(task);
        Boolean deletedTask = service.queryDeletedTask(2);
        System.out.println(deletedTask);
        //then
        assertTrue(deletedTask);
    }
}
