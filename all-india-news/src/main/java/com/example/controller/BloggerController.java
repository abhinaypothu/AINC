package com.example.controller;

import com.example.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/blogger")
public class BloggerController {

    @Autowired
    private BloggerService bloggerService;

    @GetMapping("/posts/{blogId}")
    public String getBlogPosts(@PathVariable String blogId) throws IOException {
        return bloggerService.getBlogPosts(blogId);
    }

    @PostMapping("/posts/{blogId}")
    public String createBlogPost(@PathVariable String blogId, @RequestParam String title, @RequestParam String content) throws IOException {
        return bloggerService.createBlogPost(blogId, title, content);
    }

    @GetMapping("/metrics/{blogId}/{postId}")
    public String getPostMetrics(@PathVariable String blogId, @PathVariable String postId) throws IOException {
        return bloggerService.getPostMetrics(blogId, postId);
    }

    @GetMapping("/categories/{blogId}")
    public String getCategories(@PathVariable String blogId) throws IOException {
        return bloggerService.getCategories(blogId);
    }

     @GetMapping("/tags/{blogId}")
    public String getTags(@PathVariable String blogId) throws IOException {
        return bloggerService.getTags(blogId);
    }
}
