package com.example.back_asd.Controllers;


import com.example.back_asd.controller.FeedbackController;
import com.example.back_asd.entities.Feedback;
import com.example.back_asd.services.FeedbackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class FeedbackControllerTest {

    @Mock
    private FeedbackService feedbackService;

    @InjectMocks
    private FeedbackController feedbackController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(feedbackController).build();
    }

 /*   @Test
    void testGetAllFeedbacks() throws Exception {
        List<Feedback> feedbackList = new ArrayList<>();
        feedbackList.add(new Feedback("1", "Great work!", 5, null, null));
        feedbackList.add(new Feedback("2", "Needs improvement", 3, null, null));

        when(feedbackService.getAllFeedbacks()).thenReturn(feedbackList);

        mockMvc.perform(get("/api/feedbacks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].comment").value("Great work!"))
                .andExpect(jsonPath("$[1].comment").value("Needs improvement"));

        verify(feedbackService, times(1)).getAllFeedbacks();
    }

    @Test
    void testGetFeedbackById() throws Exception {
        Feedback feedback = new Feedback("1", "Great work!", 5, null, null);

        when(feedbackService.getFeedback("1")).thenReturn(feedback);

        mockMvc.perform(get("/api/feedbacks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comment").value("Great work!"))
                .andExpect(jsonPath("$.rating").value(5));

        verify(feedbackService, times(1)).getFeedback("1");
    }

    @Test
    void testCreateFeedback() throws Exception {
        // Mocking the uploaded file
        MockMultipartFile file = new MockMultipartFile("images", "test.jpg", MediaType.IMAGE_JPEG_VALUE, "Test Image Content".getBytes());

        // Mocking the comment and rating parameters
        MockMultipartFile comment = new MockMultipartFile("comment", "", "text/plain", "Great work!".getBytes());
        MockMultipartFile rating = new MockMultipartFile("rating", "", "text/plain", "5".getBytes());

        // Mock Feedback object expected as the response
        Feedback feedback = new Feedback("1", "Great work!", 5, new ArrayList<>(), null);

        when(feedbackService.createFeedback(any(Feedback.class))).thenReturn(feedback);

        mockMvc.perform(multipart("/api/feedbacks")
                        .file(file)
                        .file(comment)
                        .file(rating)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.comment").value("Great work!"))
                .andExpect(jsonPath("$.rating").value(5));

        verify(feedbackService, times(1)).createFeedback(any(Feedback.class));
    }

    @Test
    void testUpdateFeedback() throws Exception {
        Feedback feedback = new Feedback("1", "Updated feedback", 4, null, null);

        when(feedbackService.updateFeedback(any(Feedback.class))).thenReturn(feedback);

        mockMvc.perform(put("/api/feedbacks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"comment\": \"Updated feedback\", \"rating\": 4}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comment").value("Updated feedback"))
                .andExpect(jsonPath("$.rating").value(4));

        verify(feedbackService, times(1)).updateFeedback(any(Feedback.class));
    }

    @Test
    void testDeleteFeedback() throws Exception {
        doNothing().when(feedbackService).deleteFeedback("1");

        mockMvc.perform(delete("/api/feedbacks/1"))
                .andExpect(status().isOk());

        verify(feedbackService, times(1)).deleteFeedback("1");
    }

    @Test
    public void testAssignFeedbackToProject() throws Exception {
        String feedbackId = "feedback1";
        String projectId = "project1";
        Feedback feedback = new Feedback();
        feedback.setId(feedbackId);
        feedback.setComment("Assigned Feedback");

        when(feedbackService.assignFeedbackToProject(feedbackId, projectId)).thenReturn(feedback);

        mockMvc.perform(put("/api/feedbacks/{feedbackId}/assign-project/{projectId}", feedbackId, projectId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(feedbackId))
                .andExpect(jsonPath("$.comment").value("Assigned Feedback"));

        verify(feedbackService, times(1)).assignFeedbackToProject(feedbackId, projectId);
    }*/
}
