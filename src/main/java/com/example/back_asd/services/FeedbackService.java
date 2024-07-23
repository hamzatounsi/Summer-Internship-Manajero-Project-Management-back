package com.example.back_asd.services;

import com.example.back_asd.entities.Feedback;
import com.example.back_asd.repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    public Feedback createFeedback(Feedback feedback) {
        feedback.setTask(taskService.getTask(feedback.getTask().getId()));
        feedback.setGivenBy(userService.getUser(feedback.getGivenBy().getId()));
        return feedbackRepository.save(feedback);
    }

    public Feedback getFeedback(String id) {
        return feedbackRepository.findById(id).orElseThrow();
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public Feedback updateFeedback(String id, Feedback feedbackDetails) {
        Feedback feedback = feedbackRepository.findById(id).orElseThrow();
        feedback.setComment(feedbackDetails.getComment());
        feedback.setRating(feedbackDetails.getRating());
        feedback.setTask(taskService.getTask(feedbackDetails.getTask().getId()));
        feedback.setGivenBy(userService.getUser(feedbackDetails.getGivenBy().getId()));
        return feedbackRepository.save(feedback);
    }

    public void deleteFeedback(String id) {
        feedbackRepository.deleteById(id);
    }

    // additional service methods, if needed
}
