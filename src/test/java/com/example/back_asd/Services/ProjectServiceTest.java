package com.example.back_asd.Services;

import com.example.back_asd.entities.Project;
import com.example.back_asd.entities.Task;
import com.example.back_asd.repositories.ProjectRepository;
import com.example.back_asd.services.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProject() {
        Project project = new Project();
        project.setName("New Project");
        project.setDescription("Project Description");

        when(projectRepository.save(any(Project.class))).thenReturn(project);

        Project createdProject = projectService.createProject(project);

        assertNotNull(createdProject);
        assertEquals("New Project", createdProject.getName());
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    void testGetAllProjects() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project("1", "Project 1", "Description 1", new Date(), new Date(), "High", "In Progress", new ArrayList<>(), new ArrayList<>(), false));
        projects.add(new Project("2", "Project 2", "Description 2", new Date(), new Date(), "Medium", "Not Started", new ArrayList<>(), new ArrayList<>(), false));

        when(projectRepository.findAll()).thenReturn(projects);

        List<Project> result = projectService.getAllProjects();

        assertEquals(2, result.size());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    void testGetProjectById() {
        Project project = new Project("1", "Project 1", "Description 1", new Date(), new Date(), "High", "In Progress", new ArrayList<>(), new ArrayList<>(), false);

        when(projectRepository.findById("1")).thenReturn(Optional.of(project));

        Project result = projectService.getProjectById("1");

        assertNotNull(result);
        assertEquals("Project 1", result.getName());
        verify(projectRepository, times(1)).findById("1");
    }

    @Test
    void testDeleteProject() {
        doNothing().when(projectRepository).deleteById("1");

        projectService.deleteProject("1");

        verify(projectRepository, times(1)).deleteById("1");
    }

    @Test
    void testAddTaskToProject() {
        Task task = new Task();
        task.setName("New Task");

        Project project = new Project();
        project.setId("1");
        project.setName("Project 1");
        project.setTasks(new ArrayList<>());

        when(projectRepository.findById("1")).thenReturn(Optional.of(project));
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        Project updatedProject = projectService.addTaskToProject("1", task);

        assertNotNull(updatedProject);
        assertEquals(1, updatedProject.getTasks().size());
        assertEquals("New Task", updatedProject.getTasks().get(0).getName());
        verify(projectRepository, times(1)).findById("1");
        verify(projectRepository, times(1)).save(any(Project.class));
    }
}
