// ASDStepRepository.java
package com.example.back_asd.repositories;

import com.example.back_asd.entities.ASDStep;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ASDStepRepository extends MongoRepository<ASDStep, String> {
}
