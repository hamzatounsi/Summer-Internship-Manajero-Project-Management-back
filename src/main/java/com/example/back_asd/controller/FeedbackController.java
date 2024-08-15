package com.example.back_asd.controller;

import com.example.back_asd.entities.Feedback;
import com.example.back_asd.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

   /* @GetMapping
    public List<Feedback> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }*/

    @GetMapping("/{id}")
    public Feedback getFeedbackById(@PathVariable String id) {
        return feedbackService.getFeedback(id);
    }

    @PostMapping
    public ResponseEntity<Feedback> createFeedback(MultipartHttpServletRequest request) {
        String comment = request.getParameter("comment");
        String ratingStr = request.getParameter("rating");

        // Handle the case where rating might be null or empty
        Integer rating = (ratingStr != null && !ratingStr.isEmpty()) ? Integer.parseInt(ratingStr) : null;

        Feedback feedback = new Feedback();
        feedback.setComment(comment);
        feedback.setRating(rating);

        // Handle files if any
        List<MultipartFile> files = request.getFiles("images");
        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                String fileName = file.getOriginalFilename();
                String fileUrl = saveFile(file, fileName); // Assume saveFile handles storage
                imageUrls.add(fileUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        feedback.setImageUrls(imageUrls);

        Feedback savedFeedback = feedbackService.createFeedback(feedback);
        return new ResponseEntity<>(savedFeedback, HttpStatus.CREATED);
    }


    private String saveFile(MultipartFile file, String fileName) throws IOException {
        Path directoryPath = Paths.get("uploads/");
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        Path filePath = directoryPath.resolve(fileName);
        Files.write(filePath, file.getBytes());

        // Assuming your application is running on http://localhost:8080
        // Modify to match your actual server's URL and file serving path
        return "/uploads/" + fileName;
    }






    @PutMapping("/{id}")
    public Feedback updateFeedback(@RequestBody Feedback feedbackDetails) {
        return feedbackService.updateFeedback( feedbackDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteFeedback(@PathVariable String id) {
        feedbackService.deleteFeedback(id);
    }

    @PutMapping("/{feedbackId}/assign-project/{projectId}")
    public Feedback assignFeedbackToProject(@PathVariable String feedbackId, @PathVariable String projectId) {
        return feedbackService.assignFeedbackToProject(feedbackId, projectId);
    }

    @PutMapping("/{id}/archive")
    public ResponseEntity<Feedback> archiveFeedback(@PathVariable String id) {
        Feedback feedback = feedbackService.getFeedback(id);
        if (feedback != null) {
            feedback.setArchived(true);
            feedbackService.updateFeedback(feedback);
            return ResponseEntity.ok(feedback);
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}/restore")
    public ResponseEntity<Feedback> restoreFeedback(@PathVariable String id) {
        Feedback feedback = feedbackService.getFeedback(id);
        if (feedback != null && feedback.isArchived()) {
            feedback.setArchived(false);
            feedbackService.updateFeedback(feedback);
            return ResponseEntity.ok(feedback);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Feedback> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/archived")
    public List<Feedback> getAllArchivedFeedbacks() {
        return feedbackService.getAllArchivedFeedbacks();
    }



}
