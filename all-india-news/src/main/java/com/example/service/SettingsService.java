package com.example.service;

import com.example.model.BloggerSettings;
import com.example.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {

    @Autowired
    private SettingsRepository settingsRepository;

    public BloggerSettings getSettings() {
        return settingsRepository.findById(1L).orElse(null);
    }

    public void saveSettings(BloggerSettings settings) {
        settings.setId(1L);
        settingsRepository.save(settings);
    }
}
