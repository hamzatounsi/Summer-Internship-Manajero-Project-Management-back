package com.example.back_asd.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "projects")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Project {
    @Id
    private String id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private String priority;
    private String status;

    @DBRef
    private List<Task> tasks;

    @DBRef
    private List<Feedback> feedbacks;

    private boolean isArchived = false;
    // Constructor that includes all fields
    /*public Project(String id, String name, String description, Date startDate, Date endDate, String priority, String status, List<Task> tasks, List<Feedback> feedbacks, boolean isArchived) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priority = priority;
        this.status = status;
        this.tasks = tasks;
        this.feedbacks = feedbacks;
        this.isArchived = isArchived;
    }

    // If you need a constructor without the ID (for creating new instances without setting the ID manually)
    public Project(String name, String description, Date startDate, Date endDate, String priority, String status, List<Task> tasks, List<Feedback> feedbacks, boolean isArchived) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priority = priority;
        this.status = status;
        this.tasks = tasks;
        this.feedbacks = feedbacks;
        this.isArchived = isArchived;
    }*/
}
