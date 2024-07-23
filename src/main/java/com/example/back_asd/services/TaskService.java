package com.example.back_asd.services;

import com.example.back_asd.entities.Task;
import com.example.back_asd.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;

    public Task createTask(Task task) {
        if (task.getProject() != null && task.getProject().getId() != null) {
            task.setProject(projectService.getProjectById(task.getProject().getId()));
        } else {
            throw new IllegalArgumentException("Project cannot be null");
        }
        task.setAssignedTo(userService.getUser(task.getAssignedTo().getId()));
        return taskRepository.save(task);
    }

    public Task getTask(String id) {
        return taskRepository.findById(id).orElseThrow();
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task updateTask(String id, Task taskDetails) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setName(taskDetails.getName());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        task.setProject(projectService.getProjectById(taskDetails.getProject().getId()));
        task.setAssignedTo(userService.getUser(taskDetails.getAssignedTo().getId()));
        return taskRepository.save(task);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }

    // additional service methods, if needed
}
