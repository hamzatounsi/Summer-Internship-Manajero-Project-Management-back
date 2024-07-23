package com.example.back_asd.services;

import com.example.back_asd.entities.Introduction;
import com.example.back_asd.repositories.IntroductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IntroductionService {
    @Autowired
    private IntroductionRepository repository;

    public List<Introduction> findAll() { return repository.findAll(); }
    public Introduction findById(String id) { return repository.findById(id).orElse(null); }
    public Introduction save(Introduction introduction) { return repository.save(introduction); }
    public void deleteById(String id) { repository.deleteById(id); }
    public Introduction updateIntroduction(String id, Introduction introduction) {

        introduction.setId(id);
        return repository.save(introduction);
    }
}

