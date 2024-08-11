package com.example.back_asd.repositories;

import com.example.back_asd.entities.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository <Task, String> {
    long countByProjectId(String projectId);
    long countByStatus(String status);

    List<Task> findByProjectId(String projectId);

    long countByProjectIdAndStatus(String projectId, String status);
}
