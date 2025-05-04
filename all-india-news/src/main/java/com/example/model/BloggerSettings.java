package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "blogger_settings")
public class BloggerSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "settings", columnDefinition = "TEXT")
    private String settings;

    @Column(name = "trending_blogs_count", columnDefinition = "INT DEFAULT 0")
    private int trendingBlogsCount;

    public int getTrendingBlogsCount() {
        return trendingBlogsCount;
    }

    public void setTrendingBlogsCount(int trendingBlogsCount) {
        this.trendingBlogsCount = trendingBlogsCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }
}
