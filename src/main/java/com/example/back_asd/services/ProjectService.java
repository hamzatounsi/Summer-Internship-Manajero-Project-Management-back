package com.example.back_asd.services;

import com.example.back_asd.entities.Project;
import com.example.back_asd.entities.Task;
import com.example.back_asd.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(String id) {
        return projectRepository.findById(id).orElse(null);
    }

    public void deleteProject(String id) {
        projectRepository.deleteById(id);
    }

    public Project addTaskToProject(String projectId, Task task) {
        Project project = getProjectById(projectId);
        List<Task> tasks = project.getTasks();
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        tasks.add(task);
        project.setTasks(tasks);
        return projectRepository.save(project);  // Utilisation directe de save pour enregistrer les changements
    }

    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }


}
