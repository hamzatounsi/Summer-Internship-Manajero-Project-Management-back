package com.example.back_asd.services;

import com.example.back_asd.entities.HowToImplement;
import com.example.back_asd.repositories.HowToImplementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HowToImplementService {
    @Autowired
    private HowToImplementRepository repository;

    public List<HowToImplement> findAll() { return repository.findAll(); }
    public HowToImplement findById(String id) { return repository.findById(id).orElse(null); }
    public HowToImplement save(HowToImplement howToImplement) { return repository.save(howToImplement); }
    public void deleteById(String id) { repository.deleteById(id); }
}

