package com.example.back_asd.controller;

import com.example.back_asd.entities.ProjectStatisticsDTO;
import com.example.back_asd.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/all-projects")
    public ResponseEntity<List<ProjectStatisticsDTO>> getAllProjectsWithStatistics() {
        List<ProjectStatisticsDTO> projectStatistics = statisticsService.getAllProjectsWithStatistics();
        return ResponseEntity.ok(projectStatistics);
    }

    @GetMapping("/total-projects")
    public ResponseEntity<Long> getTotalNumberOfProjects() {
        long totalProjects = statisticsService.getTotalNumberOfProjects();
        return ResponseEntity.ok(totalProjects);
    }

    @GetMapping("/total-tasks")
    public ResponseEntity<Long> getTotalNumberOfTasks() {
        long totalTasks = statisticsService.getTotalNumberOfTasks();
        return ResponseEntity.ok(totalTasks);
    }

    @GetMapping("/total-feedbacks")
    public ResponseEntity<Long> getTotalNumberOfFeedbacks() {
        long totalFeedbacks = statisticsService.getTotalNumberOfFeedbacks();
        return ResponseEntity.ok(totalFeedbacks);
    }

    @GetMapping("/average-task-completion-time")
    public ResponseEntity<Double> getAverageTaskCompletionTime() {
        double averageCompletionTime = statisticsService.getAverageTaskCompletionTime();
        return ResponseEntity.ok(averageCompletionTime);
    }

    @GetMapping("/percentage-of-completed-tasks")
    public ResponseEntity<Double> getPercentageOfCompletedTasks() {
        double percentageOfCompletedTasks = statisticsService.getPercentageOfCompletedTasks();
        return ResponseEntity.ok(percentageOfCompletedTasks);
    }

    @GetMapping("/project-average-task-completion-time")
    public ResponseEntity<Double> getAverageTaskCompletionTimeForProject(@RequestParam String projectId) {
        double averageCompletionTime = statisticsService.getAverageTaskCompletionTimeForProject(projectId);
        return ResponseEntity.ok(averageCompletionTime);
    }

    @GetMapping("/project-percentage-of-completed-tasks")
    public ResponseEntity<Double> getPercentageOfCompletedTasksForProject(@RequestParam String projectId) {
        double percentageOfCompletedTasks = statisticsService.getPercentageOfCompletedTasksForProject(projectId);
        return ResponseEntity.ok(percentageOfCompletedTasks);
    }

    // New KPI Endpoints

    @GetMapping("/total-projects-completed")
    public ResponseEntity<Long> getTotalProjectsCompleted() {
        long totalProjectsCompleted = statisticsService.getTotalProjectsCompleted();
        return ResponseEntity.ok(totalProjectsCompleted);
    }

    @GetMapping("/total-ongoing-projects")
    public ResponseEntity<Long> getTotalOngoingProjects() {
        long totalOngoingProjects = statisticsService.getTotalOngoingProjects();
        return ResponseEntity.ok(totalOngoingProjects);
    }

    @GetMapping("/average-task-duration")
    public ResponseEntity<Double> getAverageTaskDuration() {
        double averageTaskDuration = statisticsService.getAverageTaskDuration();
        return ResponseEntity.ok(averageTaskDuration);
    }

    @GetMapping("/average-feedback-rating")
    public ResponseEntity<Double> getAverageFeedbackRating() {
        double averageFeedbackRating = statisticsService.getAverageFeedbackRating();
        return ResponseEntity.ok(averageFeedbackRating);
    }

    @GetMapping("/project-completion-percentage")
    public ResponseEntity<Double> getProjectCompletionPercentage() {
        double projectCompletionPercentage = statisticsService.getProjectCompletionPercentage();
        return ResponseEntity.ok(projectCompletionPercentage);
    }

    @GetMapping("/task-completion-rate")
    public ResponseEntity<Double> getTaskCompletionRate() {
        double taskCompletionRate = statisticsService.getTaskCompletionRate();
        return ResponseEntity.ok(taskCompletionRate);
    }

    @GetMapping("/feedback-count-by-rating")
    public ResponseEntity<Map<Integer, Long>> getFeedbackCountByRating() {
        Map<Integer, Long> feedbackCountByRating = statisticsService.getFeedbackCountByRating();
        return ResponseEntity.ok(feedbackCountByRating);
    }

    @GetMapping("/task-priority-distribution")
    public ResponseEntity<Map<String, Long>> getTaskPriorityDistribution() {
        Map<String, Long> taskPriorityDistribution = statisticsService.getTaskPriorityDistribution();
        return ResponseEntity.ok(taskPriorityDistribution);
    }

    @GetMapping("/average-tasks-per-project")
    public ResponseEntity<Double> getAverageTasksPerProject() {
        double averageTasksPerProject = statisticsService.getAverageTasksPerProject();
        return ResponseEntity.ok(averageTasksPerProject);
    }
}
