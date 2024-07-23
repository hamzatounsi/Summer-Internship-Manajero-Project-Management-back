package com.example.back_asd.controller;

import com.example.back_asd.entities.ExhaustiveTutorial;
import com.example.back_asd.services.ExhaustiveTutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
}
