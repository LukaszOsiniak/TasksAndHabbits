package org.tah.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tah.model.Task;
import org.tah.service.Service;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private Service service;

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        System.out.println("getTasks - It works.");
        return service.getAllTasks();
    }

    @PostMapping("/tasks")
    public int createTask(@RequestBody Task task){
    int id = service.addTask(task);
        return id;
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask (@PathVariable(value = "id") int taskId, @RequestBody Task taskDetails){
        Task task = service.getTask(taskId);
        task.setName(taskDetails.getName());
        task.setStatus(taskDetails.getStatus());
        service.updateTask(task);
        return ResponseEntity.ok(task);
    }
}
