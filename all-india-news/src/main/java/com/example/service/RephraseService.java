package com.example.service;

import com.example.model.Rephrase;
import com.example.repository.RephraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RephraseService {

    @Autowired
    private RephraseRepository rephraseRepository;

    public List<Rephrase> getAllRephrases() {
        return rephraseRepository.findAll();
    }

    public Rephrase getRephraseById(Long id) {
        return rephraseRepository.findById(id).orElse(null);
    }

    public Rephrase createRephrase(Rephrase rephrase) {
        return rephraseRepository.save(rephrase);
    }

    public Rephrase updateRephrase(Long id, Rephrase rephrase) {
        Rephrase existingRephrase = rephraseRepository.findById(id).orElse(null);
        if (existingRephrase != null) {
            rephrase.setId(id);
            return rephraseRepository.save(rephrase);
        }
        return null;
    }

    public void deleteRephrase(Long id) {
        rephraseRepository.deleteById(id);
    }
}
