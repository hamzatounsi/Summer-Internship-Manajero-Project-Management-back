package com.example.back_asd.repositories;

import com.example.back_asd.entities.Summary;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SummaryRepository extends MongoRepository<Summary, String> {}
