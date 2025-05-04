package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "blogger_settings")
public class BloggerSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bloggerApiKey;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBloggerApiKey() {
        return bloggerApiKey;
    }

    public void setBloggerApiKey(String bloggerApiKey) {
        this.bloggerApiKey = bloggerApiKey;
    }
}
