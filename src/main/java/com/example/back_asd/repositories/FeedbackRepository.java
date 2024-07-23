package com.example.back_asd.repositories;

import com.example.back_asd.entities.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedbackRepository extends MongoRepository <Feedback, String>{
}
