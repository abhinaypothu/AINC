package com.example.service;

import com.example.model.LLMPrompt;
import com.example.repository.LLMPromptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LLMPromptService {

    @Autowired
    private LLMPromptRepository llmPromptRepository;

    public List<LLMPrompt> getAllLLMPrompts() {
        return llmPromptRepository.findAll();
    }

    public LLMPrompt getLLMPromptById(Long id) {
        return llmPromptRepository.findById(id).orElse(null);
    }

    public LLMPrompt createLLMPrompt(LLMPrompt llmPrompt) {
        return llmPromptRepository.save(llmPrompt);
    }

    public LLMPrompt updateLLMPrompt(Long id, LLMPrompt llmPrompt) {
        LLMPrompt existingLLMPrompt = llmPromptRepository.findById(id).orElse(null);
        if (existingLLMPrompt != null) {
            llmPrompt.setId(id);
            return llmPromptRepository.save(llmPrompt);
        }
        return null;
    }

    public void deleteLLMPrompt(Long id) {
        llmPromptRepository.deleteById(id);
    }
}
