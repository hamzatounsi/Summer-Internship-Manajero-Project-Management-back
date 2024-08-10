package com.example.back_asd.Services;

import com.example.back_asd.entities.Feedback;
import com.example.back_asd.entities.Project;
import com.example.back_asd.repositories.FeedbackRepository;
import com.example.back_asd.services.FeedbackService;
import com.example.back_asd.services.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FeedbackServiceTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private FeedbackService feedbackService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateFeedback() {
        Feedback feedback = new Feedback();
        feedback.setComment("Great work!");
        feedback.setRating(5);

        when(feedbackRepository.save(any(Feedback.class))).thenReturn(feedback);

        Feedback createdFeedback = feedbackService.createFeedback(feedback);
        assertEquals("Great work!", createdFeedback.getComment());
        assertEquals(5, createdFeedback.getRating());

        verify(feedbackRepository, times(1)).save(feedback);
    }

    @Test
    void testGetFeedback() {
        Feedback feedback = new Feedback();
        feedback.setId("feedback1");
        feedback.setComment("Great work!");

        when(feedbackRepository.findById("feedback1")).thenReturn(Optional.of(feedback));

        Feedback foundFeedback = feedbackService.getFeedback("feedback1");
        assertEquals("feedback1", foundFeedback.getId());
        assertEquals("Great work!", foundFeedback.getComment());

        verify(feedbackRepository, times(1)).findById("feedback1");
    }

    @Test
    void testGetAllFeedbacks() {
        List<Feedback> feedbackList = new ArrayList<>();
        Feedback feedback1 = new Feedback();
        feedback1.setComment("Great work!");
        feedback1.setRating(5);

        Feedback feedback2 = new Feedback();
        feedback2.setComment("Needs improvement");
        feedback2.setRating(3);

        feedbackList.add(feedback1);
        feedbackList.add(feedback2);

        when(feedbackRepository.findAll()).thenReturn(feedbackList);

        List<Feedback> allFeedbacks = feedbackService.getAllFeedbacks();
        assertEquals(2, allFeedbacks.size());

        verify(feedbackRepository, times(1)).findAll();
    }

    @Test
    void testUpdateFeedback() {
        Feedback feedback = new Feedback();
        feedback.setComment("Good work!");
        feedback.setRating(4);

        when(feedbackRepository.save(any(Feedback.class))).thenReturn(feedback);

        Feedback updatedFeedback = feedbackService.updateFeedback(feedback);
        assertEquals("Good work!", updatedFeedback.getComment());
        assertEquals(4, updatedFeedback.getRating());

        verify(feedbackRepository, times(1)).save(feedback);
    }

    @Test
    void testDeleteFeedback() {
        doNothing().when(feedbackRepository).deleteById("feedback1");

        feedbackService.deleteFeedback("feedback1");

        verify(feedbackRepository, times(1)).deleteById("feedback1");
    }

    @Test
    void testAssignFeedbackToProject() {
        String feedbackId = "feedback1";
        String projectId = "project1";

        Feedback feedback = new Feedback();
        feedback.setId(feedbackId);
        feedback.setComment("Great work!");

        Project project = new Project();
        project.setId(projectId);

        when(feedbackRepository.findById(feedbackId)).thenReturn(Optional.of(feedback));
        when(projectService.getProjectById(projectId)).thenReturn(project);
        when(feedbackRepository.save(any(Feedback.class))).thenReturn(feedback);

        Feedback assignedFeedback = feedbackService.assignFeedbackToProject(feedbackId, projectId);
        assertEquals(project, assignedFeedback.getProject());

        verify(feedbackRepository, times(1)).findById(feedbackId);
        verify(projectService, times(1)).getProjectById(projectId);
        verify(feedbackRepository, times(1)).save(feedback);
    }
}