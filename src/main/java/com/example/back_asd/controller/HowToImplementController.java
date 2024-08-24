package com.example.back_asd.controller;

import com.example.back_asd.entities.HowToImplement;
import com.example.back_asd.services.HowToImplementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/how-to-implement")
public class HowToImplementController {
    @Autowired
    private HowToImplementService service;

    @GetMapping
    public List<HowToImplement> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public HowToImplement findById(@PathVariable String id) { return service.findById(id); }

    @PostMapping
    public HowToImplement save(@RequestBody HowToImplement howToImplement) { return service.save(howToImplement); }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) { service.deleteById(id); }

    @PutMapping("/{id}")
    public HowToImplement update(@PathVariable String id, @RequestBody HowToImplement howToImplement) {
        // Fetch the existing HowToImplement by id
        HowToImplement existingHowToImplement = service.findById(id);
        if (existingHowToImplement != null) {
            // Update the fields with the new values
            existingHowToImplement.setStepManagement(howToImplement.getStepManagement());
            existingHowToImplement.setIterationPlanning(howToImplement.getIterationPlanning());
            existingHowToImplement.setChangeManagementForms(howToImplement.getChangeManagementForms());
            existingHowToImplement.setContinuousEvaluation(howToImplement.getContinuousEvaluation());
            // Save the updated HowToImplement back to the database
            return service.save(existingHowToImplement);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "HowToImplement not found");
        }
    }
}
