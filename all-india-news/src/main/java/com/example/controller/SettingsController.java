package com.example.controller;

import com.example.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/blogger-settings")
public class SettingsController {

    @Autowired
    private SettingsService settingsService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getSettings() {
        Map<String, Object> settings = settingsService.getSettings();
        return new ResponseEntity<>(settings, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> saveSettings(@RequestBody Map<String, Object> settings) {
        settingsService.saveSettings(settings);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
