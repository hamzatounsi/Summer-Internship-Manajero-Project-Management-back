package com.example.back_asd.repositories;

import com.example.back_asd.entities.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository <Task, String> {
}
