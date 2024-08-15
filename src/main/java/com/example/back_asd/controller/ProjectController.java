package com.example.back_asd.controller;

import com.example.back_asd.entities.Feedback;
import com.example.back_asd.entities.Project;
import com.example.back_asd.entities.Task;
import com.example.back_asd.repositories.ProjectRepository;
import com.example.back_asd.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

   // @GetMapping
    //public List<Project> getAllProjects() {
     //   return projectService.getAllProjects();
    //}

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable String id) {
        return projectService.getProjectById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable String id) {
        projectService.deleteProject(id);
    }
    // ProjectController.java
    @PutMapping("/{id}")
    public Project updateProject(@PathVariable String id, @RequestBody Project project) {
        return projectService.updateProject(project);
    }
    // New method to add a task to a project
    @PutMapping("/{projectId}/add-task")
    public Project addTaskToProject(@PathVariable String projectId, @RequestBody Task task) {
        return projectService.addTaskToProject(projectId, task);
    }

    @PutMapping("/{id}/archive")
    public ResponseEntity<Project> archiveProject(@PathVariable String id) {
        Project project = projectService.getProjectById(id);
        if (project != null) {
            project.setArchived(true);
            projectService.updateProject(project);
            return ResponseEntity.ok(project);
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}/restore")
    public ResponseEntity<Project> restoreProject(@PathVariable String id) {
        Project project = projectService.getProjectById(id);
        if (project != null && project.isArchived()) {
            project.setArchived(false);
            projectService.updateProject(project);
            return ResponseEntity.ok(project);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/archived")
    public List<Project> getAllArchivedProjects() {
        return projectService.getAllArchivedProjects();
    }
}

