package com.example.back_asd.Integrations;

import com.example.back_asd.entities.Feedback;
import com.example.back_asd.repositories.FeedbackRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class FeedbackIntegrationTest {

    @Autowired
    private FeedbackRepository feedbackRepository;

    private Feedback feedback;

    @BeforeEach
    public void setUp() {
        feedback = new Feedback();
        feedback.setComment("Great work!");
        feedback.setRating(5);
        feedbackRepository.save(feedback);
    }

    @AfterEach
    public void tearDown() {
        feedbackRepository.deleteAll();
    }

    @Test
    public void testFindById() {
        Optional<Feedback> foundFeedback = feedbackRepository.findById(feedback.getId());
        assertThat(foundFeedback).isPresent();
        assertThat(foundFeedback.get().getComment()).isEqualTo("Great work!");
    }

    @Test
    public void testSaveFeedback() {
        Feedback newFeedback = new Feedback();
        newFeedback.setComment("Needs improvement");
        newFeedback.setRating(3);

        Feedback savedFeedback = feedbackRepository.save(newFeedback);
        assertThat(savedFeedback.getId()).isNotNull();
        assertThat(savedFeedback.getComment()).isEqualTo("Needs improvement");
    }

    @Test
    public void testDeleteFeedback() {
        feedbackRepository.delete(feedback);
        Optional<Feedback> foundFeedback = feedbackRepository.findById(feedback.getId());
        assertThat(foundFeedback).isNotPresent();
    }
}