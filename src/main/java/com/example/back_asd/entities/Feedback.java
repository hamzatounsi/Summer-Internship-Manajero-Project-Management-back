package com.example.back_asd.entities;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "feedbacks")
public class Feedback {
    @Id
    private String id;
    private String comment;
    private Integer rating;
    private List<String> imageUrls = new ArrayList<>();
    @DBRef
    private Project project;

    // New constructor to match the test parameters


}
