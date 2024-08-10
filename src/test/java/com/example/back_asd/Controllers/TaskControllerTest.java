package com.example.back_asd.Controllers;

import com.example.back_asd.controller.TaskController;
import com.example.back_asd.entities.Project;
import com.example.back_asd.entities.Task;
import com.example.back_asd.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    void testGetAllTasks() throws Exception {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("1", "Task 1", "Description 1", "To Do", "High", null, null));
        tasks.add(new Task("2", "Task 2", "Description 2", "In Progress", "Medium", null, null));

        when(taskService.getAllTasks()).thenReturn(tasks);

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Task 1"))
                .andExpect(jsonPath("$[1].name").value("Task 2"));

        verify(taskService, times(1)).getAllTasks();
    }

    @Test
    void testGetTaskById() throws Exception {
        Task task = new Task("1", "Task 1", "Description 1", "To Do", "High", null, null);

        when(taskService.getTask("1")).thenReturn(task);

        mockMvc.perform(get("/api/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Task 1"))
                .andExpect(jsonPath("$.description").value("Description 1"));

        verify(taskService, times(1)).getTask("1");
    }

    @Test
    void testCreateTask() throws Exception {
        Task task = new Task("1", "New Task", "Task Description", "To Do", "High", null, null);

        when(taskService.createTask(any(Task.class))).thenReturn(task);

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"New Task\", \"description\": \"Task Description\", \"status\": \"To Do\", \"priority\": \"High\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Task"))
                .andExpect(jsonPath("$.description").value("Task Description"));

        verify(taskService, times(1)).createTask(any(Task.class));
    }

    @Test
    void testUpdateTask() throws Exception {
        Task task = new Task("1", "Updated Task", "Updated Description", "Done", "Low", null, null);

        when(taskService.updateTask(any(Task.class))).thenReturn(task);

        mockMvc.perform(put("/api/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Updated Task\", \"description\": \"Updated Description\", \"status\": \"Done\", \"priority\": \"Low\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Task"))
                .andExpect(jsonPath("$.description").value("Updated Description"));

        verify(taskService, times(1)).updateTask(any(Task.class));
    }

    @Test
    void testDeleteTask() throws Exception {
        doNothing().when(taskService).deleteTask("1");

        mockMvc.perform(delete("/api/tasks/1"))
                .andExpect(status().isOk());

        verify(taskService, times(1)).deleteTask("1");
    }

    @Test
    public void testAssignTaskToProject() throws Exception {
        String taskId = "taskId";
        String projectId = "projectId";

        Task task = new Task();
        task.setId(taskId);
        task.setName("Test Task");

        Project project = new Project();
        project.setId(projectId);
        project.setName("Test Project");

        task.setProject(project);

        when(taskService.assignTaskToProject(taskId, projectId)).thenReturn(task);

        mockMvc.perform(put("/api/tasks/{taskId}/assign-project/{projectId}", taskId, projectId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.project.id").value(projectId))
                .andExpect(jsonPath("$.project.name").value("Test Project"));
    }
}