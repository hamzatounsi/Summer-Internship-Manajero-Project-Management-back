package com.example.back_asd.controller;


import com.example.back_asd.entities.ProjectStatisticsDTO;
import com.example.back_asd.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
