package com.example.repository;

import com.example.model.BloggerSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsRepository extends JpaRepository<BloggerSettings, Long> {
}
