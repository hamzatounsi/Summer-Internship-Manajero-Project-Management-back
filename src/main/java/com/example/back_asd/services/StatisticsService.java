package com.example.back_asd.services;

import com.example.back_asd.entities.Feedback;
import com.example.back_asd.entities.Project;
import com.example.back_asd.entities.ProjectStatisticsDTO;
import com.example.back_asd.entities.Task;
import com.example.back_asd.repositories.FeedbackRepository;
import com.example.back_asd.repositories.ProjectRepository;
import com.example.back_asd.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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
            double averageTaskCompletionTime = getAverageTaskCompletionTimeForProject(project.getId());
            double percentageOfCompletedTasks = getPercentageOfCompletedTasksForProject(project.getId());

            return new ProjectStatisticsDTO(
                    project.getId(),
                    project.getName(),
                    numberOfTasks,
                    numberOfFeedbacks,
                    averageRating,
                    averageTaskCompletionTime,
                    percentageOfCompletedTasks
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

    public double getAverageTaskCompletionTimeForProject(String projectId) {
        List<Task> tasks = taskRepository.findByProjectId(projectId);
        if (tasks != null && !tasks.isEmpty()) {
            return tasks.stream()
                    .filter(task -> task.getDeadline() != null && task.getCompletionDate() != null)
                    .mapToLong(task -> task.getCompletionDate().getTime() - task.getDeadline().getTime())
                    .average()
                    .orElse(0.0) / (1000 * 60 * 60 * 24); // Convert from milliseconds to days
        }
        return 0.0;
    }

    public double getPercentageOfCompletedTasksForProject(String projectId) {
        long totalTasks = taskRepository.countByProjectId(projectId);
        long completedTasks = taskRepository.countByProjectIdAndStatus(projectId, "completed");
        if (totalTasks > 0) {
            return (double) completedTasks / totalTasks * 100;
        }
        return 0.0;
    }

    // New methods to fix the problem

    public double getAverageTaskCompletionTime() {
        List<Task> tasks = taskRepository.findAll();
        if (tasks != null && !tasks.isEmpty()) {
            return tasks.stream()
                    .filter(task -> task.getDeadline() != null && task.getCompletionDate() != null)
                    .mapToLong(task -> task.getCompletionDate().getTime() - task.getDeadline().getTime())
                    .average()
                    .orElse(0.0) / (1000 * 60 * 60 * 24); // Convert from milliseconds to days
        }
        return 0.0;
    }

    public double getPercentageOfCompletedTasks() {
        long totalTasks = taskRepository.count();
        long completedTasks = taskRepository.countByStatus("Done");
        if (totalTasks > 0) {
            return (double) completedTasks / totalTasks * 100;
        }
        return 0.0;
    }

    public long getTotalProjectsCompleted() {
        return projectRepository.countByStatus("Completed");
    }

    public long getTotalOngoingProjects() {
        return projectRepository.countByStatus("In Progress");
    }
    public double getAverageTaskDuration() {
        List<Task> tasks = taskRepository.findAll();
        if (tasks != null && !tasks.isEmpty()) {
            return tasks.stream()
                    .filter(task -> task.getCompletionDate() != null && task.getDeadline() != null)
                    .mapToLong(task -> task.getCompletionDate().getTime() - task.getDeadline().getTime())
                    .average()
                    .orElse(0.0) / (1000 * 60 * 60 * 24); // Convert from milliseconds to days
        }
        return 0.0;
    }

    public double getAverageFeedbackRating() {
        List<Feedback> feedbacks = feedbackRepository.findAll();
        if (feedbacks != null && !feedbacks.isEmpty()) {
            return feedbacks.stream()
                    .mapToInt(Feedback::getRating)
                    .average()
                    .orElse(0.0);
        }
        return 0.0;
    }
    public double getProjectCompletionPercentage() {
        long totalProjects = projectRepository.count();
        long completedProjects = projectRepository.countByStatus("Completed");
        if (totalProjects > 0) {
            return (double) completedProjects / totalProjects * 100;
        }
        return 0.0;
    }

    public double getTaskCompletionRate() {
        long totalTasks = taskRepository.count();
        long completedTasks = taskRepository.countByStatus("Done");
        if (totalTasks > 0) {
            return (double) completedTasks / totalTasks * 100;
        }
        return 0.0;
    }
    public Map<Integer, Long> getFeedbackCountByRating() {
        List<Feedback> feedbacks = feedbackRepository.findAll();
        return feedbacks.stream()
                .collect(Collectors.groupingBy(Feedback::getRating, Collectors.counting()));
    }

    public Map<String, Long> getTaskPriorityDistribution() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .collect(Collectors.groupingBy(Task::getPriority, Collectors.counting()));
    }


    public double getAverageTasksPerProject() {
        long totalProjects = projectRepository.count();
        long totalTasks = taskRepository.count();
        if (totalProjects > 0) {
            return (double) totalTasks / totalProjects;
        }
        return 0.0;
    }





}