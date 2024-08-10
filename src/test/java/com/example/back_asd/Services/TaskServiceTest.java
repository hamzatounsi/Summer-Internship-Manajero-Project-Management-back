package com.example.back_asd.Services;


import com.example.back_asd.entities.Project;
import com.example.back_asd.entities.Task;
import com.example.back_asd.repositories.TaskRepository;
import com.example.back_asd.services.ProjectService;
import com.example.back_asd.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTask() {
        Task task = new Task();
        task.setName("New Task");
        task.setDescription("Task Description");

        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task createdTask = taskService.createTask(task);

        assertNotNull(createdTask);
        assertEquals("New Task", createdTask.getName());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void testGetAllTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("1", "Task 1", "Description 1", "To Do", "High", null, null));
        tasks.add(new Task("2", "Task 2", "Description 2", "In Progress", "Medium", null, null));

        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> result = taskService.getAllTasks();

        assertEquals(2, result.size());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void testGetTask() {
        Task task = new Task("1", "Task 1", "Description 1", "To Do", "High", null, null);

        when(taskRepository.findById("1")).thenReturn(Optional.of(task));

        Task result = taskService.getTask("1");

        assertNotNull(result);
        assertEquals("Task 1", result.getName());
        verify(taskRepository, times(1)).findById("1");
    }

    @Test
    void testUpdateTask() {
        Task task = new Task("1", "Updated Task", "Updated Description", "Done", "Low", null, null);

        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task updatedTask = taskService.updateTask(task);

        assertNotNull(updatedTask);
        assertEquals("Updated Task", updatedTask.getName());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void testDeleteTask() {
        doNothing().when(taskRepository).deleteById("1");

        taskService.deleteTask("1");

        verify(taskRepository, times(1)).deleteById("1");
    }

    @Test
    void testAssignTaskToProject() {
        Task task = new Task("1", "Task 1", "Description 1", "To Do", "High", null, null);
        Project project = new Project("1", "Project 1", "Project Description", null, null, "High", "In Progress", null, null);

        when(taskRepository.findById("1")).thenReturn(Optional.of(task));
        when(projectService.getProjectById("1")).thenReturn(project);
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task updatedTask = taskService.assignTaskToProject("1", "1");

        assertNotNull(updatedTask);
        assertEquals("Project 1", updatedTask.getProject().getName());
        verify(taskRepository, times(1)).findById("1");
        verify(projectService, times(1)).getProjectById("1");
        verify(taskRepository, times(1)).save(task);
    }
}