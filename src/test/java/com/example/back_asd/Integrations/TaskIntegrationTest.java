package com.example.back_asd.Integrations;

import com.example.back_asd.entities.Task;
import com.example.back_asd.repositories.TaskRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class TaskIntegrationTest {

    @Autowired
    private TaskRepository taskRepository;

    private Task task;

    @BeforeEach
    public void setUp() {
        task = new Task();
        task.setName("New Task");
        task.setDescription("Task Description");
        taskRepository.save(task);
    }

    @AfterEach
    public void tearDown() {
        taskRepository.deleteAll();
    }

    @Test
    public void testFindById() {
        Optional<Task> foundTask = taskRepository.findById(task.getId());
        assertThat(foundTask).isPresent();
        assertThat(foundTask.get().getName()).isEqualTo("New Task");
    }

    @Test
    public void testSaveTask() {
        Task newTask = new Task();
        newTask.setName("Another Task");
        newTask.setDescription("Another Description");

        Task savedTask = taskRepository.save(newTask);
        assertThat(savedTask.getId()).isNotNull();
        assertThat(savedTask.getName()).isEqualTo("Another Task");
    }

    @Test
    public void testDeleteTask() {
        taskRepository.delete(task);
        Optional<Task> foundTask = taskRepository.findById(task.getId());
        assertThat(foundTask).isNotPresent();
    }
}