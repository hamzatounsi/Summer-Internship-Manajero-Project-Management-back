// ASDStepService.java
package com.example.back_asd.services;

import com.example.back_asd.entities.ASDStep;
import com.example.back_asd.repositories.ASDStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ASDStepService {

    @Autowired
    private ASDStepRepository repository;

    public List<ASDStep> getAllSteps() {
        return repository.findAll();
    }

    public ASDStep createStep(ASDStep step) {
        return repository.save(step);
    }

    public ASDStep updateStep(String id, ASDStep step) {
        ASDStep existingStep = repository.findById(id).orElseThrow(() -> new RuntimeException("Step not found"));
        existingStep.setStep(step.getStep());
        existingStep.setLabel(step.getLabel());
        existingStep.setTitle(step.getTitle());
        existingStep.setContent(step.getContent());
        return repository.save(existingStep);
    }

    public void deleteStep(String id) {
        repository.deleteById(id);
    }
}
