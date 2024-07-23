package com.example.back_asd.services;

import com.example.back_asd.entities.Summary;
import com.example.back_asd.repositories.SummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SummaryService {
    @Autowired
    private SummaryRepository repository;

    public List<Summary> findAll() { return repository.findAll(); }
    public Summary findById(String id) { return repository.findById(id).orElse(null); }
    public Summary save(Summary summary) { return repository.save(summary); }
    public void deleteById(String id) { repository.deleteById(id); }
}

