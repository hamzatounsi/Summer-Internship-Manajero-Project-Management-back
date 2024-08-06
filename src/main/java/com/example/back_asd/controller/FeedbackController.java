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

    @GetMapping
    public List<Feedback> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/{id}")
    public Feedback getFeedbackById(@PathVariable String id) {
        return feedbackService.getFeedback(id);
    }

    @PostMapping
    public ResponseEntity<Feedback> createFeedback(MultipartHttpServletRequest request) {
        String comment = request.getParameter("comment");
        Integer rating = Integer.parseInt(request.getParameter("rating"));

        Feedback feedback = new Feedback();
        feedback.setComment(comment);
        feedback.setRating(rating);

        List<MultipartFile> files = request.getFiles("images");
        List<String> imageUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                // Save file to server or cloud storage and get its URL/path
                String fileName = file.getOriginalFilename();
                String fileUrl = saveFile(file, fileName); // Implement this method
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

        return filePath.toString(); // Return the path or URL of the saved file
    }



    @PutMapping("/{id}")
    public Feedback updateFeedback(@RequestBody Feedback feedbackDetails) {
        return feedbackService.updateFeedback( feedbackDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteFeedback(@PathVariable String id) {
        feedbackService.deleteFeedback(id);
    }
}
