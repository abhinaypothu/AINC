package com.example.service;

import com.example.model.LLMTemplate;
import com.example.model.LLMTemplate;
import com.example.model.User;
import com.example.repository.LLMTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LLMTemplateService {

    @Autowired
    private LLMTemplateRepository llmTemplateRepository;

    public List<LLMTemplate> getAllLLMTemplates() {
        return llmTemplateRepository.findAll();
    }

    public LLMTemplate getLLMTemplateById(Long id) {
        return llmTemplateRepository.findById(id).orElse(null);
    }

    public List<LLMTemplate> getLLMTemplatesByUser(User user) {
        return llmTemplateRepository.findByUser(user);
    }

    public LLMTemplate createLLMTemplate(LLMTemplate llmTemplate) {
        return llmTemplateRepository.save(llmTemplate);
    }

    public LLMTemplate updateLLMTemplate(Long id, LLMTemplate llmTemplate) {
        LLMTemplate existingLLMTemplate = llmTemplateRepository.findById(id).orElse(null);
        if (existingLLMTemplate != null) {
            llmTemplate.setId(id);
            return llmTemplateRepository.save(llmTemplate);
        }
        return null;
    }

    public void deleteLLMTemplate(Long id) {
        llmTemplateRepository.deleteById(id);
    }
}
