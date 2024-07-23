package com.example.back_asd.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "introductions")
public class Introduction {
    @Id
    private String id;
    private String origins;
    private String methodology;
    private String impact;
    private List<String> collaboration;
    private List<String> speculations;
    private List<String> learning;
    private String imageUrl;


}
