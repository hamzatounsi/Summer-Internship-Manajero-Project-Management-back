package com.example.back_asd.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "summaries")
public class Summary {
    @Id
    private String id;
    private String introduction;
    private String corePrinciples;
    private String keyProcesses;
    private String advantagesLimitations;


}
