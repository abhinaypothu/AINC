package com.example.controller;

import com.example.model.BloggerSettings;
import com.example.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogger-settings")
public class SettingsController {

    @Autowired
    private SettingsService settingsService;

    @GetMapping
    public BloggerSettings getSettings() {
        return settingsService.getSettings();
    }

    @PostMapping
    public BloggerSettings saveSettings(@RequestBody BloggerSettings settings) {
        settingsService.saveSettings(settings);
        return settingsService.getSettings();
    }
}
