package com.example.back_asd.entities;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "feedbacks")
public class Feedback {
    @Id
    private String id;
    @DBRef
    private Task task;
    @DBRef
    private User givenBy;
    private String comment;
    private Integer rating;

    // getters and setters
}

