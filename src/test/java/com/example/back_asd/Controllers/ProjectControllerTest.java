package com.example.back_asd.Controllers;

import com.example.back_asd.controller.ProjectController;
import com.example.back_asd.entities.Project;
import com.example.back_asd.entities.Task;
import com.example.back_asd.services.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProjectControllerTest {

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
        objectMapper = new ObjectMapper(); // Initialisation de l'ObjectMapper
    }

    @Test
    void testGetAllProjects() throws Exception {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project("1", "Project 1", "Description 1", new Date(), new Date(), "High", "In Progress", null, null));
        projects.add(new Project("2", "Project 2", "Description 2", new Date(), new Date(), "Medium", "To Do", null, null));

        when(projectService.getAllProjects()).thenReturn(projects);

        mockMvc.perform(get("/projects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Project 1"))
                .andExpect(jsonPath("$[1].name").value("Project 2"));

        verify(projectService, times(1)).getAllProjects();
    }

    @Test
    void testGetProjectById() throws Exception {
        Project project = new Project("1", "Project 1", "Description 1", new Date(), new Date(), "High", "In Progress", null, null);

        when(projectService.getProjectById("1")).thenReturn(project);

        mockMvc.perform(get("/projects/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Project 1"))
                .andExpect(jsonPath("$.description").value("Description 1"));

        verify(projectService, times(1)).getProjectById("1");
    }

    @Test
    void testCreateProject() throws Exception {
        Project project = new Project("1", "New Project", "Project Description", new Date(), new Date(), "High", "In Progress", null, null);

        when(projectService.createProject(any(Project.class))).thenReturn(project);

        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"New Project\", \"description\": \"Project Description\", \"priority\": \"High\", \"status\": \"In Progress\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Project"))
                .andExpect(jsonPath("$.description").value("Project Description"));

        verify(projectService, times(1)).createProject(any(Project.class));
    }

    @Test
    void testUpdateProject() throws Exception {
        Project project = new Project("1", "Updated Project", "Updated Description", new Date(), new Date(), "Low", "Completed", null, null);

        when(projectService.updateProject(any(Project.class))).thenReturn(project);

        mockMvc.perform(put("/projects/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Project"))
                .andExpect(jsonPath("$.description").value("Updated Description"));

        verify(projectService, times(1)).updateProject(any(Project.class));
    }


    @Test
    void testDeleteProject() throws Exception {
        doNothing().when(projectService).deleteProject("1");

        mockMvc.perform(delete("/projects/1"))
                .andExpect(status().isOk());

        verify(projectService, times(1)).deleteProject("1");
    }

    @Test
    public void testAddTaskToProject() throws Exception {
        String projectId = "someProjectId";
        Task task = new Task();
        task.setId("task1");
        task.setName("Test Task");

        // Suppose que le projet retourné par le service après l'ajout de la tâche
        Project project = new Project();
        project.setId(projectId);
        project.setTasks(Collections.singletonList(task));

        when(projectService.addTaskToProject(eq(projectId), any(Task.class))).thenReturn(project);

        mockMvc.perform(put("/projects/{projectId}/add-task", projectId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tasks.length()").value(1))
                .andExpect(jsonPath("$.tasks[0].id").value("task1"))
                .andExpect(jsonPath("$.tasks[0].name").value("Test Task"));
    }
}