// ASDStep.java
package com.example.back_asd.entities;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "asd_steps")
public class ASDStep {
    @Id
    private String id;
    private String step;
    private String label;
    private String title;
    private String content;
}
