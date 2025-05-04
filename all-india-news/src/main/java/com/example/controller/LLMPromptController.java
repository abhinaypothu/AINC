package com.example.controller;

import com.example.model.LLMPrompt;
import com.example.service.LLMPromptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/llmprompts")
public class LLMPromptController {

    @Autowired
    private LLMPromptService llmPromptService;

    @GetMapping
    public List<LLMPrompt> getAllLLMPrompts() {
        return llmPromptService.getAllLLMPrompts();
    }

    @GetMapping("/{id}")
    public LLMPrompt getLLMPromptById(@PathVariable Long id) {
        return llmPromptService.getLLMPromptById(id);
    }

    @PostMapping
    public LLMPrompt createLLMPrompt(@RequestBody LLMPrompt llmPrompt) {
        return llmPromptService.createLLMPrompt(llmPrompt);
    }

    @PutMapping("/{id}")
    public LLMPrompt updateLLMPrompt(@PathVariable Long id, @RequestBody LLMPrompt llmPrompt) {
        return llmPromptService.updateLLMPrompt(id, llmPrompt);
    }

    @DeleteMapping("/{id}")
    public void deleteLLMPrompt(@PathVariable Long id) {
        llmPromptService.deleteLLMPrompt(id);
    }
}
