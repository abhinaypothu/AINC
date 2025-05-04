package com.example.controller;

import com.example.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/blogger")
public class BloggerController {

    @Autowired
    private BloggerService bloggerService;

    @GetMapping("/posts/{blogId}")
    public ResponseEntity<String> getBlogPosts() {
        try {
            String result = bloggerService.getBlogPosts();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error fetching blog posts: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/posts/{blogId}")
    public ResponseEntity<String> createBlogPost(@RequestParam String title, @RequestParam String content) {
        try {
            String result = bloggerService.createBlogPost(title, content);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>("Error creating blog post: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/metrics/{blogId}/{postId}")
    public ResponseEntity<String> getPostMetrics(@PathVariable String postId) {
        try {
            String result = bloggerService.getPostMetrics(postId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error fetching post metrics: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/categories/{blogId}")
    public ResponseEntity<String> getCategories() {
        try {
            String result = bloggerService.getCategories();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error fetching categories: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tags/{blogId}")
    public ResponseEntity<String> getTags() {
        try {
            String result = bloggerService.getTags();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error fetching tags: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
