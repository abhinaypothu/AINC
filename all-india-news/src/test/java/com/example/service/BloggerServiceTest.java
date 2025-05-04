package com.example.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.repository.SettingsRepository;
import com.rometools.rome.feed.synd.SyndFeed;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.model.BloggerSettings;
import com.example.service.SettingsService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class BloggerServiceTest {

    @Mock
    private SettingsService settingsService;

    @InjectMocks
    private BloggerService bloggerService;

    @Test
    public void testGetBlogPosts() throws IOException {
        Map<String, Object> settings = new HashMap<>();
        settings.put("bloggerApiKey", "testapikey");
        when(settingsService.getSettings()).thenReturn(settings);
        String posts = bloggerService.getBlogPosts();
        assertNotNull(posts);
    }

    @Test
    public void testGetTrendingBlogPosts() throws IOException {
        Map<String, Object> settings = new HashMap<>();
        settings.put("bloggerApiKey", "testapikey");
        settings.put("trendingBlogsCount", 5);
        when(settingsService.getSettings()).thenReturn(settings);
        String trendingPosts = bloggerService.getTrendingBlogPosts();
        assertNotNull(trendingPosts);
    }
}
