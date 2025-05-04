package com.example.repository;

import com.example.model.LLMTemplate;
import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LLMTemplateRepository extends JpaRepository<LLMTemplate, Long> {
    List<LLMTemplate> findByUser(User user);
}
