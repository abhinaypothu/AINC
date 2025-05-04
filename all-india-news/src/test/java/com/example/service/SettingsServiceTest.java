package com.example.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.repository.SettingsRepository;
import com.example.model.BloggerSettings;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SettingsServiceTest {

    @Mock
    private SettingsRepository settingsRepository;

    @InjectMocks
    private SettingsService settingsService;

    @Test
    public void testGetSettings() {
        // Test implementation here
    }

    @Test
    public void testSaveSettings() {
        // Test implementation here
    }
}
