package com.example.back_asd.repositories;

import com.example.back_asd.entities.HowToImplement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HowToImplementRepository extends MongoRepository<HowToImplement, String> {}
