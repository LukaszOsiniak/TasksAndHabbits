package org.tah.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tah.model.Task;
import org.tah.service.Service;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TaskController {

    private static final Logger LOG = LogManager.getLogger(TaskController.class);


    @Autowired
    private Service service;

    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable(value = "id") int taskId) {
        LOG.info(">> getTask - - It works.");
        return service.getTask(taskId);
    }

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        LOG.info("getTasks - It works.");
        return service.getAllTasks();
    }

    @PostMapping("/tasks")
    public int createTask(@RequestBody Task task) {
        return service.addTask(task);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable(value = "id") int taskId, @RequestBody Task taskDetails) {
        Task task = service.getTask(taskId);
        task.setName(taskDetails.getName());
        task.setStatus(taskDetails.getStatus());
        service.updateTask(task);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable(value = "id") int taskId) {
        service.deleteTaskById(taskId);
        LOG.info("Task has been deleted");
    }
}
