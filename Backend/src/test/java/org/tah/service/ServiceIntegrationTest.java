package org.tah.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tah.model.Task;
import org.tah.model.TaskStatusEnum;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServiceIntegrationTest {

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
}