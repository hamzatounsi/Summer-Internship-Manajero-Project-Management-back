package com.example.back_asd.controller;

import com.example.back_asd.entities.ExhaustiveTutorial;
import com.example.back_asd.services.ExhaustiveTutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/exhaustive-tutorials")
public class ExhaustiveTutorialController {
    @Autowired
    private ExhaustiveTutorialService service;

    @GetMapping
    public List<ExhaustiveTutorial> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public ExhaustiveTutorial findById(@PathVariable String id) { return service.findById(id); }

    @PostMapping
    public ExhaustiveTutorial save(@RequestBody ExhaustiveTutorial exhaustiveTutorial) { return service.save(exhaustiveTutorial); }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) { service.deleteById(id); }
    @PutMapping("/{id}")
    public ExhaustiveTutorial update(@PathVariable String id, @RequestBody ExhaustiveTutorial exhaustiveTutorial) {
        // Fetch the existing ExhaustiveTutorial by id
        ExhaustiveTutorial existingTutorial = service.findById(id);
        if (existingTutorial != null) {
            // Update the fields with the new values
            existingTutorial.setWhy(exhaustiveTutorial.getWhy());
            existingTutorial.setWhat(exhaustiveTutorial.getWhat());
            existingTutorial.setHow(exhaustiveTutorial.getHow());
            existingTutorial.setWhatIf(exhaustiveTutorial.getWhatIf());
            // Save the updated tutorial back to the database
            return service.save(existingTutorial);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tutorial not found");
        }
    }


}
