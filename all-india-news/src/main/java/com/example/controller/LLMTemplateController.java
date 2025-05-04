package com.example.controller;

import com.example.model.LLMTemplate;
import com.example.model.User;
import com.example.service.LLMTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/llmtemplates")
public class LLMTemplateController {

    @Autowired
    private LLMTemplateService llmTemplateService;

    @GetMapping
    public List<LLMTemplate> getAllLLMTemplates() {
        return llmTemplateService.getAllLLMTemplates();
    }

    @GetMapping("/{id}")
    public LLMTemplate getLLMTemplateById(@PathVariable Long id) {
        return llmTemplateService.getLLMTemplateById(id);
    }

    @GetMapping("/user/{userId}")
    public List<LLMTemplate> getLLMTemplatesByUser(@PathVariable User user) {
        return llmTemplateService.getLLMTemplatesByUser(user);
    }

    @PostMapping
    public LLMTemplate createLLMTemplate(@RequestBody LLMTemplate llmTemplate) {
        return llmTemplateService.createLLMTemplate(llmTemplate);
    }

    @PutMapping("/{id}")
    public LLMTemplate updateLLMTemplate(@PathVariable Long id, @RequestBody LLMTemplate llmTemplate) {
        return llmTemplateService.updateLLMTemplate(id, llmTemplate);
    }

    @DeleteMapping("/{id}")
    public void deleteLLMTemplate(@PathVariable Long id) {
        llmTemplateService.deleteLLMTemplate(id);
    }
}
