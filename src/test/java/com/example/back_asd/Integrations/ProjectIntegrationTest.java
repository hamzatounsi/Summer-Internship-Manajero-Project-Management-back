package com.example.back_asd.Integrations;

import com.example.back_asd.entities.Project;
import com.example.back_asd.repositories.ProjectRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class ProjectIntegrationTest {

    @Autowired
    private ProjectRepository projectRepository;

    private Project project;

    @BeforeEach
    public void setUp() {
        project = new Project();
        project.setName("New Project");
        project.setDescription("Project Description");
        projectRepository.save(project);
    }

    @AfterEach
    public void tearDown() {
        projectRepository.deleteAll();
    }

    @Test
    public void testFindById() {
        Optional<Project> foundProject = projectRepository.findById(project.getId());
        assertThat(foundProject).isPresent();
        assertThat(foundProject.get().getName()).isEqualTo("New Project");
    }

    @Test
    public void testSaveProject() {
        Project newProject = new Project();
        newProject.setName("Another Project");
        newProject.setDescription("Another Description");

        Project savedProject = projectRepository.save(newProject);
        assertThat(savedProject.getId()).isNotNull();
        assertThat(savedProject.getName()).isEqualTo("Another Project");
    }

    @Test
    public void testDeleteProject() {
        projectRepository.delete(project);
        Optional<Project> foundProject = projectRepository.findById(project.getId());
        assertThat(foundProject).isNotPresent();
    }
}