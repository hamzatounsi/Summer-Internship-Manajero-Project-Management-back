package com.example.back_asd.controller;

import com.example.back_asd.entities.Summary;
import com.example.back_asd.services.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
}

