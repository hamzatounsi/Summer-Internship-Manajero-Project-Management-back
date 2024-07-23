package com.example.back_asd.services;

import com.example.back_asd.entities.ExhaustiveTutorial;
import com.example.back_asd.repositories.ExhaustiveTutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExhaustiveTutorialService {
    @Autowired
    private ExhaustiveTutorialRepository repository;

    public List<ExhaustiveTutorial> findAll() { return repository.findAll(); }
    public ExhaustiveTutorial findById(String id) { return repository.findById(id).orElse(null); }
    public ExhaustiveTutorial save(ExhaustiveTutorial exhaustiveTutorial) { return repository.save(exhaustiveTutorial); }
    public void deleteById(String id) { repository.deleteById(id); }
}

