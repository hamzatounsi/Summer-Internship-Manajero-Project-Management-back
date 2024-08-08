package com.example.back_asd.services;


import com.example.back_asd.entities.Feedback;
import com.example.back_asd.entities.Project;
import com.example.back_asd.entities.ProjectStatisticsDTO;
import com.example.back_asd.repositories.FeedbackRepository;
import com.example.back_asd.repositories.ProjectRepository;
import com.example.back_asd.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    public long getTotalNumberOfProjects() {
        return projectRepository.count();
    }

    public long getTotalNumberOfTasks() {
        return taskRepository.count();
    }

    public long getTotalNumberOfFeedbacks() {
        return feedbackRepository.count();
    }

    public List<ProjectStatisticsDTO> getAllProjectsWithStatistics() {
        List<Project> projects = projectRepository.findAll();

        return projects.stream().map(project -> {
            long numberOfTasks = taskRepository.countByProjectId(project.getId());
            long numberOfFeedbacks = feedbackRepository.countByProjectId(project.getId());
            double averageRating = getAverageRatingForProject(project.getId());

            return new ProjectStatisticsDTO(
                    project.getId(),
                    project.getName(),
                    numberOfTasks,
                    numberOfFeedbacks,
                    averageRating
            );
        }).collect(Collectors.toList());
    }

    public double getAverageRatingForProject(String projectId) {
        List<Feedback> feedbacks = feedbackRepository.findByProjectId(projectId);
        if (feedbacks != null && !feedbacks.isEmpty()) {
            return feedbacks.stream()
                    .mapToInt(Feedback::getRating)
                    .average()
                    .orElse(0.0);
        }
        return 0.0;
    }
}
