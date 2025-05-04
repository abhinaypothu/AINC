package com.example.controller;

import com.example.model.Rephrase;
import com.example.service.RephraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rephrases")
public class RephraseController {

    @Autowired
    private RephraseService rephraseService;

    @GetMapping
    public List<Rephrase> getAllRephrases() {
        return rephraseService.getAllRephrases();
    }

    @GetMapping("/{id}")
    public Rephrase getRephraseById(@PathVariable Long id) {
        return rephraseService.getRephraseById(id);
    }

    @PostMapping
    public Rephrase createRephrase(@RequestBody Rephrase rephrase) {
        return rephraseService.createRephrase(rephrase);
    }

    @PutMapping("/{id}")
    public Rephrase updateRephrase(@PathVariable Long id, @RequestBody Rephrase rephrase) {
        return rephraseService.updateRephrase(id, rephrase);
    }

    @DeleteMapping("/{id}")
    public void deleteRephrase(@PathVariable Long id) {
        rephraseService.deleteRephrase(id);
    }
}
