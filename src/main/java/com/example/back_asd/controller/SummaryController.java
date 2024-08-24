package com.example.back_asd.controller;

import com.example.back_asd.entities.Summary;
import com.example.back_asd.services.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/summaries")
public class SummaryController {
    @Autowired
    private SummaryService service;

    @GetMapping
    public List<Summary> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public Summary findById(@PathVariable String id) { return service.findById(id); }

    @PostMapping
    public Summary save(@RequestBody Summary summary) { return service.save(summary); }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) { service.deleteById(id); }

    @PutMapping("/{id}")
    public Summary update(@PathVariable String id, @RequestBody Summary summary) {
        // Fetch the existing Summary by id
        Summary existingSummary = service.findById(id);
        if (existingSummary != null) {
            // Update the fields with the new values
            existingSummary.setIntroduction(summary.getIntroduction());
            existingSummary.setCorePrinciples(summary.getCorePrinciples());
            existingSummary.setKeyProcesses(summary.getKeyProcesses());
            existingSummary.setAdvantagesLimitations(summary.getAdvantagesLimitations());
            // Save the updated Summary back to the database
            return service.save(existingSummary);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Summary not found");
        }
    }
}

