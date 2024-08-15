package com.example.back_asd.services;

import com.example.back_asd.entities.Feedback;
import com.example.back_asd.entities.Project;
import com.example.back_asd.repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;
    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public Feedback getFeedback(String id) {
        return feedbackRepository.findById(id).orElseThrow();
    }

   /* public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }*/

    public Feedback updateFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public void deleteFeedback(String id) {
        feedbackRepository.deleteById(id);
    }

    public Feedback assignFeedbackToProject(String feedbackId, String projectId) {
        Feedback feedback = getFeedback(feedbackId);
        Project project = projectService.getProjectById(projectId);
        feedback.setProject(project);
        return updateFeedback(feedback);
    }
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll().stream()
                .filter(feedback -> !feedback.isArchived())
                .collect(Collectors.toList());
    }
    public List<Feedback> getAllArchivedFeedbacks() {
        return feedbackRepository.findAll().stream()
                .filter(Feedback::isArchived)  // Only return feedbacks that are archived
                .collect(Collectors.toList());
    }


}
