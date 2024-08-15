package com.example.back_asd.services;

import com.example.back_asd.entities.Project;
import com.example.back_asd.entities.Task;
import com.example.back_asd.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task getTask(String id) {
        return taskRepository.findById(id).orElseThrow();
    }

    /*public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }*/

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }

    public Task assignTaskToProject(String taskId, String projectId) {
        Task task = getTask(taskId);
        Project project = projectService.getProjectById(projectId);
        task.setProject(project);
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll().stream()
                .filter(feedback -> !feedback.isArchived())
                .collect(Collectors.toList());
    }
    public List<Task> getAllArchivedTasks() {
        return taskRepository.findAll().stream()
                .filter(Task::isArchived)  // Only return feedbacks that are archived
                .collect(Collectors.toList());
    }

}
