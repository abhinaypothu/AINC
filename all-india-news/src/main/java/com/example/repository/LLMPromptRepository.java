package com.example.repository;

import com.example.model.LLMPrompt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LLMPromptRepository extends JpaRepository<LLMPrompt, Long> {
}
