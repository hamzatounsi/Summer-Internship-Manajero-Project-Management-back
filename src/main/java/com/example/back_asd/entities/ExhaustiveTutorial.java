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
@Document(collection = "exhaustive_tutorials")
public class ExhaustiveTutorial {
    @Id
    private String id;
    private String why;
    private String what;
    private String how;
    private String whatIf;


}
