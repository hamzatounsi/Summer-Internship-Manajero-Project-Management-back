package com.example.back_asd.repositories;

import com.example.back_asd.entities.Introduction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IntroductionRepository extends MongoRepository<Introduction, String> {}
