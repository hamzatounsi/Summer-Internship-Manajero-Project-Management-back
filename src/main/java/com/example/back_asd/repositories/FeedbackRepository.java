package com.example.back_asd.repositories;

import com.example.back_asd.entities.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FeedbackRepository extends MongoRepository <Feedback, String>{
    long countByProjectId(String projectId);

    List<Feedback> findByProjectId(String projectId);
}
