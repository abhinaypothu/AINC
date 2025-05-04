package com.example.service;

import com.example.model.BloggerSettings;
import com.example.repository.SettingsRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SettingsService {

    @Autowired
    private SettingsRepository settingsRepository;

    private final Gson gson = new Gson();

    public Map<String, Object> getSettings() {
        BloggerSettings bloggerSettings = settingsRepository.findById(1L).orElse(null);
        if (bloggerSettings == null) {
            return createDefaultSettings();
        }
        String settingsJson = bloggerSettings.getSettings();
        return gson.fromJson(settingsJson, Map.class);
    }

    public BloggerSettings saveSettings(Map<String, Object> settings) {
        BloggerSettings bloggerSettings = new BloggerSettings();
        bloggerSettings.setId(1L);
        String settingsJson = gson.toJson(settings);
        bloggerSettings.setSettings(settingsJson);
        return settingsRepository.save(bloggerSettings);
    }

    private Map<String, Object> createDefaultSettings() {
        Map<String, Object> defaultSettings = new HashMap<>();
        defaultSettings.put("bloggerApiKey", "");
        defaultSettings.put("trendingBlogsCount", 5);
        BloggerSettings bloggerSettings = new BloggerSettings();
        bloggerSettings.setId(1L);
        String settingsJson = gson.toJson(defaultSettings);
        bloggerSettings.setSettings(settingsJson);
        settingsRepository.save(bloggerSettings);
        return defaultSettings;
    }
}
