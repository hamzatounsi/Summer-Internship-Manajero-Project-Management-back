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
@Document(collection = "how_to_implement")
public class HowToImplement {
    @Id
    private String id;
    private String applicationDashboard;
    private String stepManagement;
    private String iterationPlanning;
    private String changeManagementForms;
    private String continuousEvaluation;


}
