package com.example.back_asd.controller;

import com.example.back_asd.entities.HowToImplement;
import com.example.back_asd.services.HowToImplementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
}
